package com.blackship.battlesheep.communication.packet;

import java.util.Collection;
import java.util.Set;

/**
 * @author milosz
 * @since 31.07.2017
 *
 * Represents packet with updated 2 boards in battleship game.
 */
public interface PacketBoards {
    /**
     * Return player positions to update board.
     * @return Set<Integer> positions to set player board.
     */
    Set<Integer> getPlayerPositions();

    /**
     * Return enemy positions to update board.
     * @return Set<Integer> positions to set enemy board.
     */
    Set<Integer> getEnemyPositions();

    /**
     * Method add positions to enemy/player container of positions.
     * @param positions Enemy/player container.
     * @param addedPositions Positions to add to container.
     * @return True if container has changed or else return false.
     */
    Boolean addPositions(Set<Integer> positions, Collection<? extends Integer> addedPositions);

    /**
     * Method swap player positions with enemy positions.
     * @return packet with swapped positions.
     */
    PacketBoards swapBoards();
}
