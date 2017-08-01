package com.blackship.battlesheep.communication.network;

<<<<<<< HEAD
import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
import org.apache.commons.lang3.builder.EqualsBuilder;
=======
import com.blackship.battlesheep.communication.network.packet.NetworkPacketConverter;
import com.blackship.battlesheep.communication.network.packet.PacketFactory;
>>>>>>> dev_communication_milosz
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

<<<<<<< HEAD
import static org.testng.Assert.assertTrue;

=======
>>>>>>> dev_communication_milosz
/**
 * @author milosz
 * @since 28.07.2017
 */
public class AppClientSocketIT {

<<<<<<< HEAD
=======
    private byte[] readBytesFromServer(Socket givenClientSocket) throws IOException {
        return new NetworkReader(
                new NetworkStreams(givenClientSocket)
                        .getInput()
                        .orElseThrow(() -> new IOException("no inputStream !"))
        ).read();

    }

>>>>>>> dev_communication_milosz
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
<<<<<<< HEAD
=======
                givenHandler.connectWithServer();
                givenHandler.receiveBoards();
>>>>>>> dev_communication_milosz
                givenHandler.startClientLoop();
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        connectAndHandle.start();

        Socket givenClientSocket = givenServerSocket.accept();

<<<<<<< HEAD
        byte [] receivedBytesFromClient = new NetworkReader(
                new NetworkStreams(givenClientSocket)
                        .getInput()
                        .orElseThrow(() -> new IOException("no inputStream !"))
                ).read();
        givenClientSocket.getOutputStream().write(receivedBytesFromClient);
=======
        givenClientSocket.getOutputStream().write(
                new NetworkPacketConverter().toByte(
                        PacketFactory.createBoards().setCreationTime(LocalTime.now())));
        byte [] receivedMoveBytesFromClient = readBytesFromServer(givenClientSocket);
        givenClientSocket.getOutputStream().write(receivedMoveBytesFromClient);
>>>>>>> dev_communication_milosz

        connectAndHandle.join();

        //then no exception expected
    }
}
