package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.network.packet.NetworkPacketMove;
import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * @author milosz
 * @since 30.07.2017
 */
public class AppClientHandler {

    private static final Logger log = LogUtils.getLogger();

    private AppClientCommunicationHandler clientCommunicationHandler;

    public AppClientHandler(AppClientCommunicationHandler clientCommunicationHandler) {
        this.clientCommunicationHandler = clientCommunicationHandler;
    }

    public AppClientHandler connectWithServer() throws IOException {
        log.info("...Connecting...");
        clientCommunicationHandler.connect();
        log.info("...Connected...");

        return this;
    }

    public AppClientHandler startClientLoop() throws IOException, ClassNotFoundException {
        NetworkPacketMove packageToSend = PacketFactory.createMove();
        packageToSend.addPositions(Arrays.asList(1, 2));
        packageToSend.setCreationTime(LocalTime.now());

        log.info("...Sending packet...");
        clientCommunicationHandler.write(packageToSend);
        log.info(String.format("...%s has been sent...", packageToSend));

        log.info("...Reading from server...");
        Packet receivedPacket = clientCommunicationHandler.read();
        log.info(String.format("...Received: %s %s", receivedPacket, receivedPacket.getCreationTime()));

        return this;
    }

    public AppClientHandler receiveBoards() throws IOException, ClassNotFoundException {
        log.info("...Reading boards from server...");
        Packet receivedPacket = clientCommunicationHandler.read();
        log.info(String.format("...Received: %s %s", receivedPacket, receivedPacket.getCreationTime()));

        return this;
    }
}
