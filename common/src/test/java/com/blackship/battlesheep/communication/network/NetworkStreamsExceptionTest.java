package com.blackship.battlesheep.communication.network;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author Mateusz Słaboński
 * @since 28.07.2017
 */
public class NetworkStreamsExceptionTest {

    private Socket givenSocket;
    private NetworkStreams givenNetworkStream;

    @BeforeTest
    public void setUpSocket() {
        givenSocket = mock(Socket.class);
        givenNetworkStream = new NetworkStreams(givenSocket);
    }

    @Test
    public void shouldReturnNoOutputStream() throws IOException {

        //given

        //when
        when(givenSocket.getOutputStream()).thenThrow(new IOException());
        Optional<OutputStream> givenOutputStream = givenNetworkStream.getOutput();

        //then
        assertEquals(givenOutputStream, Optional.empty());
    }

    @Test
    public void shouldReturnNoInputStream() throws IOException {

        //given

        //when
        when(givenSocket.getInputStream()).thenThrow(new IOException());
        Optional<InputStream> givenInputStream = givenNetworkStream.getInput();

        //then
        assertEquals(givenInputStream, Optional.empty());
    }

}
