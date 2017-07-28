package com.blackship.battlesheep.communication.packet;

import java.io.IOException;

/**
 * @author milosz
 * @since 28.07.2017
 *
 * Convert:
 *  Packet to bytes,
 *  bytes to Packet.
 */
public interface PacketConverter {
    /**
     * Method convert Packet to bytes.
     * @param packet Given packet.
     * @return bytes.
     * @throws IOException Exception if there is a problem with stream.
     */
    byte[] toByte(Packet packet) throws IOException;

    /**
     * Method convert bytes to Packet.
     * @param bytes Given bytes.
     * @return Packet.
     * @throws IOException Exception if there is a problem with stream.
     * @throws ClassNotFoundException Exception if can't cast to Packet.
     */
    Packet toPacket(byte[] bytes) throws IOException, ClassNotFoundException;
}
