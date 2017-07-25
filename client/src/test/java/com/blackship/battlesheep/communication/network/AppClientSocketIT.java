package com.blackship.battlesheep.communication.network;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Timer;
import java.util.TimerTask;

public class AppClientSocketIT {

    @Test
    public void connectAndExpectNoExceptionTest() throws IOException {

        //given
        int givenPort = 8085;
        AppClientSocket givenClientSocket = new AppClientSocket("localhost", givenPort);
        ServerSocket givenServerSocket = new ServerSocket(givenPort);

        //when
        TimerTask connectTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    givenClientSocket.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Timer().schedule(connectTask, 500);
        givenServerSocket.accept();

        //then no exception expected
    }
}
