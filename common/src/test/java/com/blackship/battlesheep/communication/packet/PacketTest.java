package com.blackship.battlesheep.communication.packet;

import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import org.testng.annotations.Test;

import java.time.LocalTime;

import static org.testng.Assert.assertEquals;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class PacketTest {

    @Test
    public void shouldSetCreationTimeInPacket() {

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