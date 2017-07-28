package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.Reader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class NetworkReader implements Reader {

    private InputStream inputStream;

    public NetworkReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public byte[] read() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        for(int inputValue = inputStream.read(); inputValue != -1; inputValue = inputStream.read()) {
            byteArrayOutputStream.write(inputValue);
            if (inputStream.available() <= 0) break;
        }

        return byteArrayOutputStream.toByteArray();
    }
}
