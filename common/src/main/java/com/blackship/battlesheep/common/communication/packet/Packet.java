package com.blackship.battlesheep.common.communication.packet;

import com.blackship.battlesheep.common.communication.packet.enums.PacketType;

import java.io.Serializable;
import java.time.LocalTime;

public interface Packet extends Serializable {
    PacketType getPacketType();
    LocalTime getCreationTime();
    Packet setCreationTime(LocalTime time);
}
