package com.blackship.battlesheep.communication.packet;

import com.blackship.battlesheep.communication.packet.enums.PacketType;

import java.time.LocalTime;

/**
 * @author milosz
 * @since 28.07.2017
 */
public abstract class BasePacket implements Packet {

    protected PacketType packetType;
    private LocalTime time;

    @Override
    public PacketType getPacketType() {
        return packetType;
    }

    @Override
    public LocalTime getCreationTime() {
        return time;
    }

    @Override
    public Packet setCreationTime(LocalTime time) {
        this.time = time;

        return this;
    }

    @Override
    public String toString() {
        return "Packet " + packetType;
    }
}
