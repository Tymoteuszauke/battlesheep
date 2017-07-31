package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.network.packet.NetworkPacketMove;
import com.blackship.battlesheep.communication.network.packet.PacketFactory;

import java.io.IOException;
import java.time.LocalTime;

public class AppClientHandler {

    private AppClientCommunicationHandler clientCommunicationHandler;

    AppClientHandler(AppClientCommunicationHandler clientCommunicationHandler) {
        this.clientCommunicationHandler = clientCommunicationHandler;
    }

    public void startClientLoop() throws IOException, ClassNotFoundException {
        clientCommunicationHandler.connect();

        NetworkPacketMove packageToSend = PacketFactory.createMove();
        packageToSend.addPosition(1);
        packageToSend.setCreationTime(LocalTime.now());

        clientCommunicationHandler.write(packageToSend);

        clientCommunicationHandler.read();
    }
}
