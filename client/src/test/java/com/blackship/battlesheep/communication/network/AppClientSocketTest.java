package com.blackship.battlesheep.communication.network;

import org.testng.annotations.Test;

import java.net.Socket;

import static org.testng.Assert.assertNotNull;

public class AppClientSocketTest {

    @Test
    public void getSocketAndExpectNotNullObjectTest() {

        //given
        AppClientSocket givenClientSocket = new AppClientSocket("localhost", 8085);

        //when
        Socket givenExpectedSocket = givenClientSocket.getSocket();

        //then
        assertNotNull(givenExpectedSocket);
    }
}