package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.PacketBoards;
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
        PacketBoards givenPacketBoard = PacketFactory.createBoards();
        List<Integer> givenPositions = Arrays.asList(1, 2, 3);

        //when
        Boolean givenAddStatementPlayer = givenPacketBoard.addPositions(givenPacketBoard.getPlayerPositions(), givenPositions);
        Boolean givenAddStatementEnemy = givenPacketBoard.addPositions(givenPacketBoard.getEnemyPositions(), givenPositions);
        Set<Integer> givenPlayerPositions = givenPacketBoard.getPlayerPositions();
        Set<Integer> givenEnemyPositions = givenPacketBoard.getEnemyPositions();

        //then
        assertTrue(givenAddStatementPlayer);
        assertTrue(givenAddStatementEnemy);
        assertTrue(givenPlayerPositions.containsAll(givenPositions));
        assertTrue(givenEnemyPositions.containsAll(givenPositions));
    }

    @Test
    public void shouldSwapPlayerBoardWithEnemyBoard() {

        //given
        PacketBoards givenPacketBoard = PacketFactory.createBoards();

        List<Integer> playerPositions = Arrays.asList(1, 2);
        List<Integer> enemyPositions = Arrays.asList(3, 4);

        givenPacketBoard.addPositions(givenPacketBoard.getPlayerPositions(), playerPositions);
        givenPacketBoard.addPositions(givenPacketBoard.getEnemyPositions(), enemyPositions);

        //when
        givenPacketBoard.swapBoards();

        //then
        assertTrue(givenPacketBoard.getEnemyPositions().containsAll(playerPositions));
        assertTrue(givenPacketBoard.getPlayerPositions().containsAll(enemyPositions));
    }
}