package com.blackship.battlesheep.common.communication.packet;

import com.blackship.battlesheep.common.communication.packet.enums.PacketType;

import java.time.LocalTime;

public abstract class BasePacket implements Packet {

    protected PacketType packetType;
    protected LocalTime time;

}
