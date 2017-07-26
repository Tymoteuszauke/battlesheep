package com.blackship.battlesheep.common.communication.packet;

import com.blackship.battlesheep.common.communication.network.packet.PacketFactory;
import org.testng.annotations.Test;

import java.time.LocalTime;

import static org.testng.Assert.assertEquals;


public class PacketTest {

    @Test
    public void setCreationTimeInPacketAndExpectCorrectTimeWithMethodGetCreationTimeTest() {

        //given
        LocalTime expectedTime = LocalTime.now();
        Packet givenPacket = PacketFactory.createMove();

        //when
        givenPacket.setCreationTime(expectedTime);
        LocalTime givenTime = givenPacket.getCreationTime();

        //then
        assertEquals(givenTime, expectedTime);
    }

}