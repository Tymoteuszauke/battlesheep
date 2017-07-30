package com.blackship.blacksheep.communication.network;

import com.blackship.battlesheep.communication.network.NetworkReader;
import com.blackship.battlesheep.communication.network.NetworkWriter;
import com.blackship.battlesheep.communication.network.packet.NetworkPacketConverter;
import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;

import static org.testng.Assert.assertTrue;

/**
 * @author Mateusz Słaboński
 * @since 25.07.2017
 */
public class AppServerSocketIT {

    private static Boolean sameSentPacketFromFirstClient;
    private static Boolean sameSentPacketFromSecondClient;

    private Boolean clientEcho(Integer givenThreadSleepTime, Integer givenPort) {
        Boolean samePacketAsSent;
        try {
            Thread.sleep(givenThreadSleepTime);
            Socket givenClient = new Socket("localhost", givenPort);

            Packet givenPacketToSend = PacketFactory.createMove().setCreationTime(LocalTime.now());
            byte[] givenPacketToSendAsBytes = new NetworkPacketConverter().toByte(givenPacketToSend);

            new NetworkWriter(givenClient.getOutputStream())
                    .write(givenPacketToSendAsBytes);

            byte[] receivedPacketAsBytes = new NetworkReader(givenClient.getInputStream()).read();
            Packet receivedPacket = new NetworkPacketConverter().toPacket(receivedPacketAsBytes);

            samePacketAsSent = EqualsBuilder.reflectionEquals(givenPacketToSend, receivedPacket);
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
            samePacketAsSent = false;
        }

        return samePacketAsSent;
    }

    @Test(timeOut = 2000)
    public void shouldConnectAndSentReceivedPacket() throws IOException, ClassNotFoundException, InterruptedException {

        //given
        int givenPort = 8282;
        Integer givenThreadSleepTime = 500;
        ServerCommunicationHandler givenServerHandler = new ServerCommunicationHandler(new AppServerSocket(givenPort));

        //when
        Thread connectFirstClientWithEcho = new Thread(() ->
                sameSentPacketFromFirstClient = clientEcho(givenThreadSleepTime, givenPort));
        connectFirstClientWithEcho.start();

        Thread connectSecondClientWithEcho = new Thread(() ->
                sameSentPacketFromSecondClient = clientEcho(givenThreadSleepTime, givenPort));
        connectSecondClientWithEcho.start();

        givenServerHandler.acceptClients().echo();

        connectFirstClientWithEcho.join();
        connectSecondClientWithEcho.join();

        //then
        assertTrue(sameSentPacketFromFirstClient);
        assertTrue(sameSentPacketFromSecondClient);
    }
}
