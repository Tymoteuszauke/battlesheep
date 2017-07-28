package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.Streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Optional;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class NetworkStreams implements Streams {

    private Socket socket;

    public NetworkStreams(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Optional<OutputStream> getOutput() {
        try {
            return Optional.ofNullable(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<InputStream> getInput() {
        try {
            return Optional.ofNullable(socket.getInputStream());
        } catch (IOException e) {
            System.err.println(e);
        }

        return Optional.empty();
    }
}
