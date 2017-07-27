package com.blackship.battlesheep.common.communication.network;

import com.blackship.battlesheep.common.communication.Writer;

import java.io.IOException;
import java.io.OutputStream;

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
