package com.blackship.battlesheep.common.communication.network.packet;

import com.blackship.battlesheep.common.communication.packet.enums.PacketType;

public class PacketFactory {

    public static NetworkPacketMove createMove() {
        return new NetworkPacketMove(PacketType.MOVE);
    }

}
