package com.blackship.battlesheep.common.communication.network;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.net.Socket;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class NetworkStreamsTest {

    private Socket givenSocket;
    private NetworkStreams givenNetworkStream;

    @BeforeTest
    public void setUpSocket() {
        givenSocket = mock(Socket.class);
        givenNetworkStream = new NetworkStreams(givenSocket);
    }

    @Test
    public void getOutputAndExpectGivenObjectTest() throws IOException {

        //given - givenSocket, givenNetworkStream
        OutputStream expectedOutputStream = new ByteArrayOutputStream(1);

        //when
        when(givenSocket.getOutputStream()).thenReturn(expectedOutputStream);
        OutputStream givenOutputStream = givenNetworkStream.getOutput().orElse(null);

        //then
        assertEquals(givenOutputStream, expectedOutputStream);
    }

    @Test
    public void getInputAndExpectNotNullObjectTest() throws IOException {

        //given
        InputStream expectedInputStream = new ByteArrayInputStream(new byte[1]);

        //when
        when(givenSocket.getInputStream()).thenReturn(expectedInputStream);
        InputStream givenInputStream = givenNetworkStream.getInput().orElse(null);

        //then
        assertEquals(givenInputStream, expectedInputStream);
    }

    @Test
    public void getOutputAndExpectOptionalEmpty() throws IOException {

        //given

        //when
        when(givenSocket.getOutputStream()).thenThrow(new IOException());
        Optional<OutputStream> givenOutputStream = givenNetworkStream.getOutput();

        //then
        assertEquals(givenOutputStream, Optional.empty());
    }

    @Test
    public void getInputAndExpectOptionalEmpty() throws IOException {

        //given

        //when
        when(givenSocket.getInputStream()).thenThrow(new IOException());
        Optional<InputStream> givenInputStream = givenNetworkStream.getInput();

        //then
        assertEquals(givenInputStream, Optional.empty());
    }

}