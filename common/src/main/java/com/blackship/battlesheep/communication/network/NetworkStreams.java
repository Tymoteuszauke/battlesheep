package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.Streams;
import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;

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

    private final static Logger log = LogUtils.getLogger();

    private Socket socket;

    public NetworkStreams(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Optional<OutputStream> getOutput() {
        try {
            return Optional.ofNullable(socket.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<InputStream> getInput() {
        try {
            return Optional.ofNullable(socket.getInputStream());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return Optional.empty();
    }
}
