package com.blackship.battlesheep.communication.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

class AppClientSocket {

    private final String host;
    private final int port;
    private final Socket socket;

    AppClientSocket(String host, int port) {
        this.host = host;
        this.port = port;
        socket = new Socket();
    }

    Socket getSocket() {
        return socket;
    }

    void connect() throws IOException {
        socket.connect(new InetSocketAddress(host, port));
    }
}
