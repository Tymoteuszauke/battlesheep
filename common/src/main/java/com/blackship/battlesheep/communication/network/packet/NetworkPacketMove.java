package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.BasePacket;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketMove;
import com.blackship.battlesheep.communication.packet.enums.PacketType;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author milosz
 * @since 28.07.2017
 */
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
