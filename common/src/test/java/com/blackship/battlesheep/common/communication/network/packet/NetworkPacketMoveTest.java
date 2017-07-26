package com.blackship.battlesheep.common.communication.network.packet;

import com.blackship.battlesheep.common.communication.packet.PacketMove;
import com.blackship.battlesheep.common.communication.packet.enums.PacketType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NetworkPacketMoveTest {

    @DataProvider(name = "packetTypes")
    public static Object[][] packetTypes() {
        return new Object[][] { { PacketType.MOVE }, { PacketType.UPDATE }, { PacketType.ERROR } };
    }

    @Test(dataProvider = "packetTypes")
    public void getPacketTypeFromPacketAndExpectGivenTypeTest(PacketType expectedPacketType) {

        //given - expectedPacketType

        //when
        PacketType givenPacketType = new NetworkPacketMove(expectedPacketType).getPacketType();

        //then
        assertEquals(expectedPacketType, givenPacketType);
    }

    @Test
    public void addPositionInPacketMoveAndExpectPutPositionInSetInPacketMoveTest() {

        //given
        PacketMove givenPacketMove = new NetworkPacketMove(PacketType.MOVE);
        Integer givenPosition = 1;

        //when
        Boolean givenAddStatement = givenPacketMove.addPosition(givenPosition);
        Set<Integer> givenPositions = givenPacketMove.getPositions();

        //then
        assertTrue(givenAddStatement);
        assertTrue(givenPositions.contains(givenPosition));
    }
}