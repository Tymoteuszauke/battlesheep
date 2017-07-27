package com.blackship.battlesheep.common.communication.network.packet;

import com.blackship.battlesheep.common.communication.packet.Packet;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author milosz
 * @since 7/27/17
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
