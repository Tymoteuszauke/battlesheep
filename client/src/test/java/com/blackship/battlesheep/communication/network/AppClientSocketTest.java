package com.blackship.battlesheep.communication.network;

import org.testng.annotations.Test;

import java.net.Socket;

import static org.testng.Assert.assertNotNull;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class AppClientSocketTest {

    @Test
    public void shouldReturnSocket() {

        //given
        AppClientSocket givenClientSocket = new AppClientSocket("localhost", 8085);

        //when
        Socket givenExpectedSocket = givenClientSocket.getSocket();

        //then
        assertNotNull(givenExpectedSocket);
    }
}