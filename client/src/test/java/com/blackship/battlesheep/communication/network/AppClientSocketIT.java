package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.common.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.common.communication.packet.Packet;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

import static org.testng.Assert.assertTrue;

public class AppClientSocketIT {

    private static Boolean receivedPacketIsTheSameAsSent;

    @Test
    public void shouldConnectAndReceiveSentPackage() throws IOException, InterruptedException {

        //given
        Integer givenPort = 8085;
        AppClientCommunicationHandler givenHandler = new AppClientCommunicationHandler(new AppClientSocket("localhost", 8085));
        ServerSocket givenServerSocket = new ServerSocket(givenPort);
        Packet givenPacketToSend = PacketFactory.createMove().setCreationTime(LocalTime.now());

        //when
        Thread connectAndHandle = new Thread(() -> {
            try {
                Thread.sleep(500);
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

        byte[] buffer = new byte[2048];
        givenClientSocket.getInputStream().read(buffer);
        givenClientSocket.getOutputStream().write(buffer);

        connectAndHandle.join();

        //then no exception expected
        assertTrue(receivedPacketIsTheSameAsSent);
    }
}
