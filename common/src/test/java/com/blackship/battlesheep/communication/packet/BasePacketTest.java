package com.blackship.battlesheep.communication.packet;

import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.enums.PacketType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author milosz
 * @since 07.08.2017
 */
public class BasePacketTest {

    @DataProvider
    public static Object[][] packets() {
        return new Object[][] { { PacketFactory.createBoard() },
                                { PacketFactory.createMove() },
                                { PacketFactory.createWinner() } };
    }

    @Test(dataProvider = "packets")
    public void shouldReturnCorrectRepresentationAsString(Packet givenPacket) {

        //given - givenPacket

        //when
        String givenRepresentationAsString = givenPacket.toString();

        //then
        assertEquals(givenRepresentationAsString, "Packet " + givenPacket.getPacketType());
    }

}