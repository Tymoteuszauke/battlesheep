package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.Streams;
import com.blackship.battlesheep.communication.network.packet.NetworkPacketConverter;
import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketMove;
import com.blackship.battlesheep.game.state.exceptions.WrongStateException;
import com.blackship.battlesheep.game.state.fleet.FleetGenerator;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.testng.Assert.assertTrue;

/**
 * @author Mateusz Słaboński
 * @since 25.07.2017
 */
public class AppServerSocketIT {

    private Socket clientConnection(Integer givenThreadSleepTime, Integer givenPort) throws IOException, InterruptedException {
        Thread.sleep(givenThreadSleepTime);

        return new Socket("localhost", givenPort);
    }

    private void sendPacket(Socket socket, List<Integer> positions) throws IOException {
        PacketMove givenPacketMove = PacketFactory.createMove();
        givenPacketMove.addPositions(positions);

        new NetworkWriter(socket.getOutputStream())
                .write(new NetworkPacketConverter().toByte((Packet)givenPacketMove));
    }

    @Test(timeOut = 4000)
    public void shouldConnectAndWinGame() throws IOException, ClassNotFoundException, InterruptedException, WrongStateException {

        //given
        int givenPort = 8282;
        Integer givenThreadSleepTime = 500;
        ServerCommunicationHandler givenServerHandler = new ServerCommunicationHandler(new AppServerSocket(givenPort));

        //when
        Thread connectFirstClientWithEcho = new Thread(() -> {
            try {
                Socket clientWinSocket = clientConnection(givenThreadSleepTime, givenPort);

                List<Integer> winPositions = new FleetGenerator().hardcodeShips()
                        .stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());

                Integer lastPositionForWin = winPositions.get(winPositions.size() - 1);
                winPositions.remove(winPositions.size() - 1);

                sendPacket(clientWinSocket, winPositions);

                new NetworkReader(clientWinSocket.getInputStream()).read();

                winPositions.add(lastPositionForWin);

                sendPacket(clientWinSocket, winPositions);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                assertTrue(false);
            }
        });
        connectFirstClientWithEcho.start();

        Thread connectSecondClientWithEcho = new Thread(() -> {
            try {
                Socket clientWinSocket = clientConnection(givenThreadSleepTime, givenPort);

                ArrayList<Integer> emptyList = new ArrayList<>();

                sendPacket(clientWinSocket, emptyList);

                new NetworkReader(clientWinSocket.getInputStream()).read();

                sendPacket(clientWinSocket, emptyList);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                assertTrue(false);
            }
        });
        connectSecondClientWithEcho.start();

        givenServerHandler
                .acceptClients()
                .echo(true);

        connectFirstClientWithEcho.join();
        connectSecondClientWithEcho.join();

        //then no exceptions
    }
}
