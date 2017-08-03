package com.blackship.battlesheep.communication.network;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.Socket;

import static org.testng.Assert.assertTrue;

/**
 * @author Mateusz Słaboński
 * @since 25.07.2017
 */
public class AppServerSocketIT {

    private void clientConnection(Integer givenThreadSleepTime, Integer givenPort) throws IOException, InterruptedException {
        Thread.sleep(givenThreadSleepTime);
        new Socket("localhost", givenPort);
    }

    @Test(timeOut = 4000)
    public void shouldConnectAndSentReceivedPacket() throws IOException, ClassNotFoundException, InterruptedException {

        //given
        int givenPort = 8282;
        Integer givenThreadSleepTime = 500;
        ServerCommunicationHandler givenServerHandler = new ServerCommunicationHandler(new AppServerSocket(givenPort));

        //when
        Thread connectFirstClientWithEcho = new Thread(() -> {
            try {
                clientConnection(givenThreadSleepTime, givenPort);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                assertTrue(false);
            }
        });
        connectFirstClientWithEcho.start();

        Thread connectSecondClientWithEcho = new Thread(() -> {
            try {
                clientConnection(givenThreadSleepTime, givenPort);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                assertTrue(false);
            }
        });
        connectSecondClientWithEcho.start();

        givenServerHandler.acceptClients();

        connectFirstClientWithEcho.join();
        connectSecondClientWithEcho.join();

        //then no exceptions
    }
}
