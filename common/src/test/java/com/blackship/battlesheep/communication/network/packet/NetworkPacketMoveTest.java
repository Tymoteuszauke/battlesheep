package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.PacketMove;
import com.blackship.battlesheep.communication.packet.enums.PacketType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class NetworkPacketMoveTest {

    @DataProvider
    public static Object[][] packetTypes() {
        return new Object[][] { { PacketType.MOVE }, { PacketType.UPDATE }, { PacketType.ERROR } };
    }

    @Test(dataProvider = "packetTypes")
    public void shouldReturnCorrectPacketTypeFromPacket(PacketType expectedPacketType) {

        //given - expectedPacketType

        //when
        PacketType givenPacketType = new NetworkPacketMove(expectedPacketType).getPacketType();

        //then
        assertEquals(expectedPacketType, givenPacketType);
    }

    @Test
    public void shouldAddPositionToPacketMoveAndPutPositionInMoveSet() {

        //given
        PacketMove givenPacketMove = PacketFactory.createMove();
        List<Integer> givenPositionsToAdd = Arrays.asList(1, 2);

        //when
        Boolean givenAddStatement = givenPacketMove.addPositions(givenPositionsToAdd);
        List<Integer> givenPositions = givenPacketMove.getPositions().get(0);

        //then
        assertTrue(givenAddStatement);
        assertTrue(givenPositions.containsAll(givenPositionsToAdd));
    }
}