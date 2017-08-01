package com.blackship.blacksheep.communication.network;

import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketBoards;
import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    public ServerCommunicationHandler sendHardcodedBoardsToClients() throws IOException {
        PacketBoards boardForSent = PacketFactory.createBoards();
        boardForSent.addPositions(boardForSent.getEnemyPositions(), Arrays.asList(1, 2));
        boardForSent.addPositions(boardForSent.getEnemyPositions(), Arrays.asList(3, 4));

        ClientSocketHandler firstClient = clients.get(0);
        ClientSocketHandler secondClient = clients.get(1);

        firstClient.write(((Packet)boardForSent).setCreationTime(LocalTime.now()));
        secondClient.write(((Packet)boardForSent.swapBoards()).setCreationTime(LocalTime.now()));

        return this;
    }

    public void echo() throws IOException, ClassNotFoundException {
        ClientSocketHandler firstClient = clients.get(0);
        ClientSocketHandler secondClient = clients.get(1);

        log.info("...Receiving packet...");
        Packet receivedPacket = firstClient.read();
        log.info("..." + receivedPacket.getPacketType() + " has been received from client " + firstClient +"...");
        firstClient.write(receivedPacket);
        log.info("..." + receivedPacket + " has been sent to " + firstClient + "...");

        log.info("...Receiving packet...");
        receivedPacket = secondClient.read();
        log.info("..." + receivedPacket + " has been received from client " + secondClient +"...");
        secondClient.write(receivedPacket);
        log.info("..." + receivedPacket + " has been sent to " + secondClient + "...");
    }

}
