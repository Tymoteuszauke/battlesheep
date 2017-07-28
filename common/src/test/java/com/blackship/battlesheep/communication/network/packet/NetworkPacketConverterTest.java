package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.Packet;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class NetworkPacketConverterTest {

    @Test
    public void shouldReturnBytesFromPacketAndPacketFromBytes() throws IOException, ClassNotFoundException {

        //given
        Packet expectedPacket = PacketFactory.createMove();
        NetworkPacketConverter givenPacketConverter = new NetworkPacketConverter();

        //when
        byte[] givenPacketAsBytes = givenPacketConverter.toByte(expectedPacket);
        Packet givenPacket = givenPacketConverter.toPacket(givenPacketAsBytes);

        //then
        assertTrue(EqualsBuilder.reflectionEquals(givenPacket, expectedPacket));
    }

}