package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.BasePacket;
import com.blackship.battlesheep.communication.packet.PacketBoards;
import com.blackship.battlesheep.communication.packet.enums.PacketType;

import java.util.*;

/**
 * @author milosz
 * @since 31.07.2017
 */
public class NetworkPacketBoard extends BasePacket implements PacketBoards {

    private Set<Integer> playerPositions;
    private Set<Integer> enemyPositions;

    NetworkPacketBoard(PacketType packetType) {
        super.packetType = packetType;
        this.playerPositions = new HashSet<>();
        this.enemyPositions = new HashSet<>();
    }

    @Override
    public Set<Integer> getPlayerPositions() {
        return playerPositions;
    }

    @Override
    public Set<Integer> getEnemyPositions() {
        return enemyPositions;
    }

    @Override
    public Boolean addPositions(Set<Integer> positions, Collection<? extends Integer> addedPositions) {
        return positions.addAll(addedPositions);
    }

    @Override
    public NetworkPacketBoard swapBoards() {
        List<Integer> tempList = new ArrayList<>(playerPositions);
        playerPositions.clear();
        playerPositions.addAll(enemyPositions);
        enemyPositions.clear();
        enemyPositions.addAll(tempList);

        return this;
    }
}
