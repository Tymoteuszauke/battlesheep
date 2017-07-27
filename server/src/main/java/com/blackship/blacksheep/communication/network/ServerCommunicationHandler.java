package com.blackship.blacksheep.communication.network;

import com.blackship.battlesheep.common.communication.packet.Packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author milosz
 * @since 7/26/17
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

        clients.add(new ClientSocketHandler(server.getSockets().get(0)));

        return this;
    }

    void echo() throws IOException, ClassNotFoundException {
        ClientSocketHandler client = clients.get(0);

        Packet receivedPacket = client.read();

        client.write(receivedPacket);
    }

}
