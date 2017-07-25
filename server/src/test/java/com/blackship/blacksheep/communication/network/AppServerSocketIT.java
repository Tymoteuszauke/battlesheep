package com.blackship.blacksheep.communication.network;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by Mateusz on 25.07.2017.
 */
@Test
public class AppServerSocketIT {

    public void socketConnectedTestExpectTrueTest() throws IOException {
        //given
        int givenPort = 8282;
        AppServerSocket appServerSocket = new AppServerSocket(givenPort);

        TimerTask timeTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("localhost", givenPort);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Timer().schedule(timeTask, 1000);

        //when
        boolean givenStatement = appServerSocket.acceptClient();

        //then
        assertFalse(appServerSocket.getSockets().isEmpty());
        assertTrue(givenStatement);
    }
}
