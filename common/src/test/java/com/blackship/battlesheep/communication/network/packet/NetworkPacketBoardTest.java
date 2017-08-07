package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.PacketBoard;
import com.blackship.battlesheep.communication.packet.enums.PacketType;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author milosz
 * @since 31.07.2017
 */
public class NetworkPacketBoardTest {

    @Test(dataProvider = "packetTypes", dataProviderClass = NetworkPacketMoveTest.class)
    public void shouldReturnCorrectPacketTypeFromPacket(PacketType expectedPacketType) {

        //given - expectedPacketType

        //when
        PacketType givenPacketType = new NetworkPacketBoard(expectedPacketType).getPacketType();

        //then
        assertEquals(expectedPacketType, givenPacketType);
    }


    @Test
    public void shouldAddPositionsToPacketBoardsAndPutPositionsInBoardsSet() {

        //given
        PacketBoard givenPacketBoard = PacketFactory.createBoard();
        List<List<Integer>> givenPositions = Arrays.asList(Arrays.asList(1, 2, 3));

        //when
        Boolean givenAddStatementPlayer = givenPacketBoard.addPositions(givenPositions);
        List<List<Integer>> givenPlayerPositions = givenPacketBoard.getPlayerPositions();

        //then
        assertTrue(givenAddStatementPlayer);
        assertTrue(givenPlayerPositions.containsAll(givenPositions));
    }
}