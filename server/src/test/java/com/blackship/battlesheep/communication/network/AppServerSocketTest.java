package com.blackship.battlesheep.communication.network;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Mateusz Słaboński
 * @since 25.07.2017
 */
@Test
public class AppServerSocketTest {

    private final int expectedPort = 8282;
    private AppServerSocket appServerSocket;

    @BeforeMethod
    public void initializeBattleshipServerSocket() throws IOException {
        appServerSocket = new AppServerSocket(expectedPort);
    }

    @AfterMethod
    public void shouldCloseServerSocket() throws IOException {
        appServerSocket.getServerSocket().close();
    }

    public void shouldReturnCorrectPortNumber() throws IOException {
        //given battleshipServerSocket

        //when
        int givenPort = appServerSocket.getPort();

        //then
        assertEquals(givenPort, expectedPort);
    }

    public void shouldReturnServerSocket() throws IOException {
        //given battleshipServerSocket

        //when
        ServerSocket givenServerSocket = appServerSocket.getServerSocket();

        //then
        assertNotNull(givenServerSocket);
    }




}