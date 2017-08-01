package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.BasePacket;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketMove;
import com.blackship.battlesheep.communication.packet.enums.PacketType;

import java.time.LocalTime;
import java.util.Collection;
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
    public Set<Integer> getPositions() {
        return positions;
    }

    @Override
    public Boolean addPositions(Collection<? extends Integer> positionsToAdd) {
        return positions.addAll(positionsToAdd);
    }

}
