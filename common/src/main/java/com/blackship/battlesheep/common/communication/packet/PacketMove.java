package com.blackship.battlesheep.common.communication.packet;

import java.util.Set;

public interface PacketMove {
    Set<Integer> getPositions();
    Boolean addPosition(Integer position);
}
