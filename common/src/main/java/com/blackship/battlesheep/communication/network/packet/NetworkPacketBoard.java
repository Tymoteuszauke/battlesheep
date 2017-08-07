package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.BasePacket;
import com.blackship.battlesheep.communication.packet.PacketBoard;
import com.blackship.battlesheep.communication.packet.enums.PacketType;

import java.util.*;

/**
 * @author milosz
 * @since 31.07.2017
 */
public class NetworkPacketBoard extends BasePacket implements PacketBoard {

    private List<List<Integer>> playerPositions;

    NetworkPacketBoard(PacketType packetType) {
        super.packetType = packetType;
        this.playerPositions = new ArrayList<>();
    }

    @Override
    public List<List<Integer>> getPlayerPositions() {
        return playerPositions;
    }

    @Override
    public Boolean addPositions(List<List<Integer>> addedPositions) {
        return playerPositions.addAll(addedPositions);
    }
}
