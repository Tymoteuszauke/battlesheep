package com.blackship.battlesheep.common.communication.network;

import com.blackship.battlesheep.common.communication.Reader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NetworkReader implements Reader {

    private InputStream inputStream;

    NetworkReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public byte[] read() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        for(int inputValue = inputStream.read(); inputValue != -1 || inputStream.available() < 0 ; inputValue = inputStream.read()) {
            byteArrayOutputStream.write(inputValue);
        }

        return byteArrayOutputStream.toByteArray();
    }
}
