package com.blackship.battlesheep.common.communication.network;

import com.blackship.battlesheep.common.communication.Writer;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.testng.Assert.*;

public class NetworkWriterTest {

    @Test
    public void writeAndExpectNoExceptionTest() throws IOException {

        //given
        Writer givenNetworkWriter = new NetworkWriter(new ByteArrayOutputStream(1));

        //when
        givenNetworkWriter.write(new byte[1]);

        //then - expect no exceptions
    }

}