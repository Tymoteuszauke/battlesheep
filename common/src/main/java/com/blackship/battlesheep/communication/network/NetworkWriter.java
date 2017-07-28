package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.Writer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class NetworkWriter implements Writer {

    private OutputStream outputStream;

    public NetworkWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        outputStream.write(bytes);
    }
}
