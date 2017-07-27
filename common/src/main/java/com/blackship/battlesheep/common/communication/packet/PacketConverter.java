package com.blackship.battlesheep.common.communication.packet;

import java.io.IOException;

/**
 * @author milosz
 * @since 7/26/17
 */
public interface PacketConverter {
    byte[] toByte(Packet packet) throws IOException;
    Packet toPacket(byte[] bytes) throws IOException, ClassNotFoundException;
}
