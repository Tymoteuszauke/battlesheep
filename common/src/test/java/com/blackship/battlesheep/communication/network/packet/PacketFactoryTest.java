package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.Packet;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class PacketFactoryTest {

    @Test
    public void shouldCreateNetworkMovePacket() {

        //given

        //when
        Packet givenPacket = PacketFactory.createMove();

        //then
        assertTrue(givenPacket instanceof NetworkPacketMove);
    }
}
