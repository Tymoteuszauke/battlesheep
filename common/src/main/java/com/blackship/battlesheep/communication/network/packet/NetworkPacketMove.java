package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.BasePacket;
import com.blackship.battlesheep.communication.packet.PacketMove;
import com.blackship.battlesheep.communication.packet.enums.PacketType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class NetworkPacketMove extends BasePacket implements PacketMove {

    private List<List<Integer>> positions;

    NetworkPacketMove(PacketType packetType) {
        this.packetType = packetType;
        positions = new ArrayList<>();
    }

    @Override
    public List<List<Integer>> getPositions() {
        return positions;
    }

    @Override
    public Boolean addPositions(List<Integer> positionsToAdd) {
        return positions.add(positionsToAdd);
    }

}
