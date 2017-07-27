package com.blackship.blacksheep.communication.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 25.07.2017.
 */
public class AppServerSocket {

    private final int port;
    private ServerSocket serverSocket;
    private List<Socket> sockets;

    public AppServerSocket(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.sockets = new ArrayList<>();
    }

    public int getPort() {
        return port;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public boolean acceptClient() throws IOException {
        Socket socket = serverSocket.accept();
        return sockets.add(socket);
    }

    public List<Socket> getSockets() {
        return sockets;
    }
}
