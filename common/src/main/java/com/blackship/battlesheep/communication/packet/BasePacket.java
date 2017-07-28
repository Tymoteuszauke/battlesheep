package com.blackship.battlesheep.communication.packet;

import com.blackship.battlesheep.communication.packet.enums.PacketType;

import java.time.LocalTime;

/**
 * @author milosz
 * @since 28.07.2017
 */
public abstract class BasePacket implements Packet {

    protected PacketType packetType;
    protected LocalTime time;

}
