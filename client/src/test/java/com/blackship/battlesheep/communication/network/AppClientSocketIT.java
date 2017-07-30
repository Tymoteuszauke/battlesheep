package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

import static org.testng.Assert.assertTrue;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class AppClientSocketIT {

    @Test(timeOut = 2000)
    public void shouldConnectAndReceiveSentPackage() throws IOException, InterruptedException {

        //given
        String givenHost = "localhost";
        Integer givenPort = 8085;
        Integer givenThreadSleepTime = 500;
        AppClientCommunicationHandler givenCommunicationHandler = new AppClientCommunicationHandler(new AppClientSocket(givenHost, givenPort));
        AppClientHandler givenHandler = new AppClientHandler(givenCommunicationHandler);
        ServerSocket givenServerSocket = new ServerSocket(givenPort);

        //when
        Thread connectAndHandle = new Thread(() -> {
            try {
                Thread.sleep(givenThreadSleepTime);
                givenHandler.startClientLoop();
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        connectAndHandle.start();

        Socket givenClientSocket = givenServerSocket.accept();

        byte [] receivedBytesFromClient = new NetworkReader(
                new NetworkStreams(givenClientSocket)
                        .getInput()
                        .orElseThrow(() -> new IOException("no inputStream !"))
                ).read();
        givenClientSocket.getOutputStream().write(receivedBytesFromClient);

        connectAndHandle.join();

        //then no exception expected
    }
}
