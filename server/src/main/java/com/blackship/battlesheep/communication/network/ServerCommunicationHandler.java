package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.bus.Event;
import com.blackship.battlesheep.bus.EventBus;
import com.blackship.battlesheep.bus.Listener;
import com.blackship.battlesheep.communication.network.packet.NetworkPacketBoard;
import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
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

    private final static Logger log = LogUtils.getLogger();

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

    public ServerCommunicationHandler sendRandomBoardToClients() throws IOException {
        ClientSocketHandler firstClient = clients.get(0);
        ClientSocketHandler secondClient = clients.get(1);

        sendRandomBoard(firstClient);
        sendRandomBoard(secondClient);

        return this;
    }

    void sendRandomBoard(ClientSocketHandler client) throws IOException {
        NetworkPacketBoard networkPacketBoard = PacketFactory.createBoard();
        networkPacketBoard.addPositions(new FleetGenerator().generateRandomFleet());

        client.write(networkPacketBoard.setCreationTime(LocalTime.now()));
    }

    PacketMove receivePacketMove(ClientSocketHandler client) throws IOException, ClassNotFoundException {
        log.info("...Receiving packet...");
        Packet receivedPacketFirstPlayer = client.read();
        PacketMove receivedPacketMoveFirstPlayer = (PacketMove) receivedPacketFirstPlayer;
        log.info("..." + receivedPacketFirstPlayer.getPacketType() + " has been received from client " + client + "...");

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
        log.info("..." + packetMove + " has been sent to " + client + "...");
    }

    public void echo() throws IOException, ClassNotFoundException, WrongStateException, InterruptedException {

        ClientSocketHandler firstClient = clients.get(0);
        ClientSocketHandler secondClient = clients.get(1);

        Game game = new Game();
        game.startGame(FleetGenerator.hardcodeShips(), FleetGenerator.hardcodeShips());

        EventBus winnerBus = new EventBus();
        winnerBus.register(this);
        new Thread(winnerBus).start();

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
