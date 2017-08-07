package com.blackship.battlesheep.communication.packet.enums;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author milosz
 * @since 07.08.2017
 */
@Test
public class PacketTypeTest {

    public void shouldReturnCorrectValue() {

        //given
        PacketType expectedPacketType = PacketType.BOARD;
        String givenPacketTypeAsString = expectedPacketType.name();

        //when
        PacketType givenPacketType = PacketType.valueOf(givenPacketTypeAsString);

        //then
        assertEquals(givenPacketType, expectedPacketType);
    }

}