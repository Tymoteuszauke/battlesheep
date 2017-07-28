package com.blackship.battlesheep.communication.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author milosz
 * @since 28.07.2017
 */
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
