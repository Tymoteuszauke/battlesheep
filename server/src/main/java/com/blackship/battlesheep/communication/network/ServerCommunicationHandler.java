package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.bus.Event;
import com.blackship.battlesheep.bus.EventBus;
import com.blackship.battlesheep.bus.Listener;
import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketBoard;
import com.blackship.battlesheep.communication.packet.PacketMove;
import com.blackship.battlesheep.game.Game;
import com.blackship.battlesheep.game.state.FinishedGameState;
import com.blackship.battlesheep.game.state.exceptions.WrongStateException;
import com.blackship.battlesheep.game.state.fleet.FleetGenerator;
import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author milosz
 * @since 27.7.17
 */
public class ServerCommunicationHandler implements Listener {

    private static final Logger log = LogUtils.getLogger();

    private AppServerSocket server;
    private List<ClientSocketHandler> clients;

    public ServerCommunicationHandler(AppServerSocket server) {
        this.server = server;
        clients = new ArrayList<>();
    }

    public ServerCommunicationHandler acceptClients() throws IOException {
        log.info("...Waiting for clients...");
        server.acceptClient();
        log.info("...First client connected...");
        server.acceptClient();
        log.info("...Second client connected...");

        clients.add(new ClientSocketHandler(server.getSockets().get(0)));
        clients.add(new ClientSocketHandler(server.getSockets().get(1)));

        return this;
    }

    public ServerCommunicationHandler sendRandomBoardToClients(
            List<List<Integer>> firstPlayerBoard,
            List<List<Integer>> secondPlayerBoard
            ) throws IOException {
        ClientSocketHandler firstClient = clients.get(0);
        ClientSocketHandler secondClient = clients.get(1);

        PacketBoard firstClientPacket = PacketFactory.createBoard();
        firstClientPacket.addPositions(firstPlayerBoard);

        PacketBoard secondClientPacket = PacketFactory.createBoard();
        secondClientPacket.addPositions(secondPlayerBoard);

        log.info("...Sending board to first client...");
        firstClient.write(((Packet)firstClientPacket).setCreationTime(LocalTime.now()));
        log.info("...Sending board to second client...");
        secondClient.write(((Packet)secondClientPacket).setCreationTime(LocalTime.now()));

        return this;
    }

    PacketMove receivePacketMove(ClientSocketHandler client) throws IOException, ClassNotFoundException {
        log.info("...Receiving packet...");
        Packet receivedPacketFirstPlayer = client.read();
        PacketMove receivedPacketMoveFirstPlayer = (PacketMove) receivedPacketFirstPlayer;

        log.info(String.format("...%s has been receinved from client %s ...", receivedPacketFirstPlayer.getPacketType(), client));
        return receivedPacketMoveFirstPlayer;
    }

    List<List<Integer>> playerShotPositions(List<List<Integer>> listOfPositions) {
        List<List<Integer>> playerShotPositions = new ArrayList<>(1);
        playerShotPositions.add(listOfPositions.get(0));

        return playerShotPositions;
    }

    PacketMove createPacketMoveWithResponse(List<List<Integer>> shotPositions) {
        PacketMove packetMoveWithResult = PacketFactory.createMove();
        packetMoveWithResult.addPositions(shotPositions.get(0));
        packetMoveWithResult.addPositions(shotPositions.get(1));

        return packetMoveWithResult;
    }

    PacketMove swapPositionsInPacketMove(PacketMove packetMove) {
        Collections.swap(packetMove.getPositions(), 0, 1);
        return  packetMove;
    }

    void sendShotPositions(PacketMove packetMove, ClientSocketHandler client) throws IOException {
        client.write(((Packet)packetMove).setCreationTime(LocalTime.now()));
        log.info(String.format("...%s has been sent to %s...", packetMove,  client));
    }

    public void echo() throws IOException, ClassNotFoundException, WrongStateException, InterruptedException {
        log.info("..Starting echo...");
        ClientSocketHandler firstClient = clients.get(0);
        ClientSocketHandler secondClient = clients.get(1);

        log.info("...Generating fleets...");
        FleetGenerator fleetGenerator = new FleetGenerator();
        List<List<Integer>> firstPlayerBoard = fleetGenerator.generateRandomFleet();
        List<List<Integer>> secondPlayerBoard = fleetGenerator.generateRandomFleet();

        log.info("...Sending boards to clients...");
        sendRandomBoardToClients(firstPlayerBoard, secondPlayerBoard);

        Game game = new Game();
        game.startGame(firstPlayerBoard, secondPlayerBoard);

        EventBus winnerBus = new EventBus();
        winnerBus.register(this);
        new Thread(winnerBus).start();

        log.info("...start game loop...");
        while(true) {

            PacketMove receivedPacketFromFirstClient = receivePacketMove(firstClient);
            PacketMove receivedPacketFromSecondClient = receivePacketMove(secondClient);

            List<List<Integer>> shotPositions = game.move(
                        playerShotPositions(receivedPacketFromFirstClient.getPositions()),
                        playerShotPositions(receivedPacketFromSecondClient.getPositions())
            );

            if (game.getGameState() instanceof FinishedGameState) {
                log.info("Game won!");
                FinishedGameState winner = (FinishedGameState) game.getGameState();
                log.info(winner.getWinner());

                winnerBus.submit(new Event(new ReportClients(firstClient, secondClient, winner.getWinner())));

                break;
            }

            PacketMove shotPositionsPlayers = createPacketMoveWithResponse(shotPositions);

            sendShotPositions(shotPositionsPlayers, firstClient);

            swapPositionsInPacketMove(shotPositionsPlayers);

            sendShotPositions(shotPositionsPlayers, secondClient);

            Thread.sleep(300);
        }

        winnerBus.stop();
    }

    @Override
    public void update(Event event) {
        if (event.getObject() instanceof ReportClients) {
            ReportClients reportClients = (ReportClients) event.getObject();
            reportClients.report();
        }
    }
}