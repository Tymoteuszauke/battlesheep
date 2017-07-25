package com.blackship.blacksheep.communication.network;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by Mateusz on 25.07.2017.
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
    public void closeServerSocket() throws IOException {
        appServerSocket.getServerSocket().close();
    }

    public void getPortExpectNotZeroTest() throws IOException {
        //given battleshipServerSocket

        //when
        int givenPort = appServerSocket.getPort();

        //then
        assertEquals(givenPort, expectedPort);
    }

    public void getServerSocketExpectNotNull() throws IOException {
        //given battleshipServerSocket

        //when
        ServerSocket givenServerSocket = appServerSocket.getServerSocket();

        //then
        assertNotNull(givenServerSocket);
    }




}