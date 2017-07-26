package com.blackship.battlesheep.common.communication.packet;

import com.blackship.battlesheep.common.communication.packet.enums.PacketType;

import java.time.LocalTime;

public interface Packet {
    PacketType getPacketType();
    LocalTime getCreationTime();
    Packet setCreationTime(LocalTime time);
}
