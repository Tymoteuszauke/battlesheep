package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.Writer;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class NetworkWriterTest {

    @Test
    public void shouldWriteToStream() throws IOException {

        //given
        Writer givenNetworkWriter = new NetworkWriter(new ByteArrayOutputStream(1));

        //when
        givenNetworkWriter.write(new byte[1]);

        //then - expect no exceptions
    }

}