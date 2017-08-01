package com.blackship.battlesheep.communication.packet;

import java.util.Collection;
import java.util.Set;

/**
 * @author milosz
 * @since 28.07.2017
 *
 * Represents packet with game move in battleship game.
 */
public interface PacketMove {
    /**
     * Return positions to update board.
     * @return Set<Integer> positions to update.
     */
    Set<Integer> getPositions();

    /**
     * Method add position to the set of positions to update board.
     * @param positionsToAdd Given positions.
     * @return True if add was successful or else return false.
     */
    Boolean addPositions(Collection<? extends Integer> positionsToAdd);
}
