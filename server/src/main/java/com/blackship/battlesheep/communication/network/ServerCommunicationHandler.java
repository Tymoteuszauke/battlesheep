package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketMove;
import com.blackship.battlesheep.game.Game;
import com.blackship.battlesheep.game.state.exceptions.FirstPlayerWon;
import com.blackship.battlesheep.game.state.exceptions.SecondPlayerWon;
import com.blackship.battlesheep.game.state.fleet.FleetGenerator;
import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author milosz
 * @since 27.7.17
 */
public class ServerCommunicationHandler {

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

    public void echo() throws IOException, ClassNotFoundException, FirstPlayerWon, SecondPlayerWon {
        ClientSocketHandler firstClient = clients.get(0);
        ClientSocketHandler secondClient = clients.get(1);

        Game game = new Game();
        game.startGame(FleetGenerator.hardcodeShips(), FleetGenerator.hardcodeShips());

        while(true) {

            log.info("...Receiving packet...");
            Packet receivedPacketFirstPlayer = firstClient.read();
            PacketMove receivedPacketMoveFirstPlayer = (PacketMove) receivedPacketFirstPlayer;
            log.info("..." + receivedPacketFirstPlayer.getPacketType() + " has been received from client " + firstClient + "...");

            log.info("...Receiving packet...");
            Packet receivedPacketSecondPlayer = secondClient.read();
            PacketMove receivedPacketMoveSecondPlayer = (PacketMove) receivedPacketSecondPlayer;
            log.info("..." + receivedPacketSecondPlayer.getPacketType() + " has been received from client " + secondClient + "...");

            List<List<Integer>> firstPlayerShotPositions = new ArrayList<>(1);
            firstPlayerShotPositions.add(receivedPacketMoveFirstPlayer.getPositions().get(0));

            List<List<Integer>> secondPlayerShotPositions = new ArrayList<>(1);
            secondPlayerShotPositions.add(receivedPacketMoveSecondPlayer.getPositions().get(0));

            List<List<Integer>> shotPositions;
            try {
                shotPositions = game.move(firstPlayerShotPositions, secondPlayerShotPositions);

            } catch (FirstPlayerWon | SecondPlayerWon e) {
                log.error(e.getMessage());
                log.info("ONE PLAYER WON!!!!");
                break;
            }

            PacketMove shotPositionsForFirstPlayer = PacketFactory.createMove();
            shotPositionsForFirstPlayer.addPositions(shotPositions.get(0));
            shotPositionsForFirstPlayer.addPositions(shotPositions.get(1));

            PacketMove shotPositionsForSecondPlayer = PacketFactory.createMove();
            shotPositionsForSecondPlayer.addPositions(shotPositions.get(1));
            shotPositionsForSecondPlayer.addPositions(shotPositions.get(0));

            firstClient.write(((Packet)shotPositionsForSecondPlayer).setCreationTime(LocalTime.now()));
            log.info("..." + shotPositionsForSecondPlayer + " has been sent to " + firstClient + "...");

            secondClient.write(((Packet)shotPositionsForSecondPlayer).setCreationTime(LocalTime.now()));
            log.info("..." + shotPositionsForSecondPlayer + " has been sent to " + secondClient + "...");
        }
    }

}
