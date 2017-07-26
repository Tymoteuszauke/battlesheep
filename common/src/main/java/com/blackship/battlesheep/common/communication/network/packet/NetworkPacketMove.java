package com.blackship.battlesheep.common.communication.network.packet;

import com.blackship.battlesheep.common.communication.packet.BasePacket;
import com.blackship.battlesheep.common.communication.packet.Packet;
import com.blackship.battlesheep.common.communication.packet.PacketMove;
import com.blackship.battlesheep.common.communication.packet.enums.PacketType;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class NetworkPacketMove extends BasePacket implements PacketMove {

    private Set<Integer> positions;

    NetworkPacketMove(PacketType packetType) {
        this.packetType = packetType;
        positions = new HashSet<>();
    }

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
    public Set<Integer> getPositions() {
        return positions;
    }

    @Override
    public Boolean addPosition(Integer position) {
        return positions.add(position);
    }

}
