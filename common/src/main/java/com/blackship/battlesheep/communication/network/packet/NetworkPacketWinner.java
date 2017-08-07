package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.BasePacket;
import com.blackship.battlesheep.communication.packet.PacketWinner;
import com.blackship.battlesheep.communication.packet.enums.PacketType;

/**
 * @author milosz
 * @since 07.08.2017
 */
public class NetworkPacketWinner extends BasePacket implements PacketWinner {

    private String winner;

    NetworkPacketWinner(PacketType packetType) {
        this.packetType = packetType;
    }

    @Override
    public String getWinner() {
        return winner;
    }

    public NetworkPacketWinner setWinner(String winner) {
        this.winner = winner;

        return this;
    }
}
