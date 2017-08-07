package com.blackship.battlesheep.communication.packet;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author milosz
 * @since 31.07.2017
 *
 * Represents packet with updated board in battleship game.
 */
public interface PacketBoard {
    /**
     * Return player positions to update board.
     * @return Set<Integer> positions to set player board.
     */
    List<List<Integer>> getPlayerPositions();


    /**
     * Method add positions to player container of positions.
     * @param addedPositions Positions to add to container.
     * @return True if container has changed or else return false.
     */
    Boolean addPositions(List<List<Integer>> addedPositions);
}
