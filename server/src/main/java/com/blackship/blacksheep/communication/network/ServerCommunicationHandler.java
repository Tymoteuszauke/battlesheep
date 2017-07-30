package com.blackship.blacksheep.communication.network;

import com.blackship.battlesheep.communication.packet.Packet;
import com.sun.org.apache.regexp.internal.RE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author milosz
 * @since 27.7.17
 */
class ServerCommunicationHandler {

    private AppServerSocket server;
    private List<ClientSocketHandler> clients;

    ServerCommunicationHandler(AppServerSocket server) {
        this.server = server;
        clients = new ArrayList<>();
    }

    ServerCommunicationHandler acceptClients() throws IOException {
        server.acceptClient();
        server.acceptClient();

        clients.add(new ClientSocketHandler(server.getSockets().get(0)));
        clients.add(new ClientSocketHandler(server.getSockets().get(1)));

        return this;
    }

    void echo() throws IOException, ClassNotFoundException {
        ClientSocketHandler firstClient = clients.get(0);
        ClientSocketHandler secondClient = clients.get(1);

        Packet receivedPacket = firstClient.read();
        firstClient.write(receivedPacket);

        receivedPacket = secondClient.read();
        secondClient.write(receivedPacket);
    }

}
