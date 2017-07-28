package com.blackship.battlesheep.communication.packet;

import com.blackship.battlesheep.communication.packet.enums.PacketType;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * @author milosz
 * @since 28.07.2017
 *
 * Packet is for sending object via sockets using serialization.
 */
public interface Packet extends Serializable {
    /**
     * Method return type of packet.
     * @return Enum PacketType which contains type of packet.
     */
    PacketType getPacketType();

    /**
     * Method return creation time of packet.
     * @return Time when packet was created.
     */
    LocalTime getCreationTime();

    /**
     * Method set creation time of packet.
     * @param time Given time to set creation time of packet.
     * @return Updated packet.
     */
    Packet setCreationTime(LocalTime time);
}
