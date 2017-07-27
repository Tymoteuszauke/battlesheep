package com.blackship.blacksheep.communication.network;

import com.blackship.battlesheep.common.communication.network.NetworkReader;
import com.blackship.battlesheep.common.communication.network.NetworkWriter;
import com.blackship.battlesheep.common.communication.network.packet.NetworkPacketConverter;
import com.blackship.battlesheep.common.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.common.communication.packet.Packet;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;

import static org.testng.Assert.assertTrue;

/**
 * Created by Mateusz on 25.07.2017.
 */
public class AppServerSocketIT {

    private static Boolean receivedPacketIsTheSameAsSent;

    @Test
    public void shouldConnectAndSentReceivedPacketWithoutException() throws IOException, ClassNotFoundException, InterruptedException {

        //given
        int givenPort = 8282;
        ServerCommunicationHandler givenServerHandler = new ServerCommunicationHandler(new AppServerSocket(givenPort));

        //when
        Thread connectClientWithEcho = new Thread(() -> {

            try {
                Thread.sleep(500);
                Socket givenClient = new Socket("localhost", givenPort);

                Packet givenPacketToSend = PacketFactory.createMove().setCreationTime(LocalTime.now());
                byte[] givenPacketToSendAsBytes = new NetworkPacketConverter().toByte(givenPacketToSend);

                new NetworkWriter(givenClient.getOutputStream())
                        .write(givenPacketToSendAsBytes);

                byte[] receivedPacketAsBytes = new NetworkReader(givenClient.getInputStream()).read();
                Packet receivedPacket = new NetworkPacketConverter().toPacket(receivedPacketAsBytes);

                receivedPacketIsTheSameAsSent = EqualsBuilder.reflectionEquals(givenPacketToSend, receivedPacket);
            } catch (IOException | InterruptedException | ClassNotFoundException e) {
                e.printStackTrace();
                receivedPacketIsTheSameAsSent = false;
            }

        });
        connectClientWithEcho.start();

        givenServerHandler.acceptClients().echo();

        connectClientWithEcho.join();

        //then
        assertTrue(receivedPacketIsTheSameAsSent);
    }
}
