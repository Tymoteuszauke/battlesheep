package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.enums.PacketType;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class PacketFactory {

    private PacketFactory() {}

    public static NetworkPacketMove createMove() {
        return new NetworkPacketMove(PacketType.MOVE);
    }

}
