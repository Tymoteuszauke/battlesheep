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

    private static Boolean receivedPacketIsTheSameAsSent;

    @Test
    public void shouldConnectAndReceiveSentPackage() throws IOException, InterruptedException {

        //given
        String givenHost = "localhost";
        Integer givenPort = 8085;
        Integer givenThreadSleepTime = 500;
        AppClientCommunicationHandler givenHandler = new AppClientCommunicationHandler(new AppClientSocket(givenHost, givenPort));
        ServerSocket givenServerSocket = new ServerSocket(givenPort);
        Packet givenPacketToSend = PacketFactory.createMove().setCreationTime(LocalTime.now());

        //when
        Thread connectAndHandle = new Thread(() -> {
            try {
                Thread.sleep(givenThreadSleepTime);
                givenHandler.connect();
                givenHandler.write(givenPacketToSend);

                Packet receivedPacket =  givenHandler.read();

                receivedPacketIsTheSameAsSent = EqualsBuilder.reflectionEquals(givenPacketToSend, receivedPacket);
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
                receivedPacketIsTheSameAsSent = false;
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
        assertTrue(receivedPacketIsTheSameAsSent);
    }
}
