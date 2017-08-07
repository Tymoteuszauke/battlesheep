package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.PacketWinner;
import com.blackship.battlesheep.communication.packet.enums.PacketType;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author milosz
 * @since 07.08.2017
 */
@Test
public class NetworkPacketWinnerTest {

    @Test(dataProvider = "packetTypes", dataProviderClass = NetworkPacketMoveTest.class)
    public void shouldReturnCorrectPacketTypeFromPacket(PacketType expectedPacketType) {

        //given - expectedPacketType

        //when
        PacketType givenPacketType = new NetworkPacketWinner(expectedPacketType).getPacketType();

        //then
        assertEquals(expectedPacketType, givenPacketType);
    }

    public void shouldReturnPacketWithSetWinner() {

        //given
        NetworkPacketWinner givenPacketWinner = PacketFactory.createWinner();
        String expectedWinner = "winner";

        //when
        givenPacketWinner.setWinner(expectedWinner);
        String givenWinner = givenPacketWinner.getWinner();

        //then
        assertEquals(givenWinner, expectedWinner);
    }
}
