package com.blackship.battlesheep.communication.packet;

import java.util.List;

/**
 * @author milosz
 * @since 28.07.2017
 *
 * Represents packet with game move in battleship game.
 */
public interface PacketMove {
    /**
     * Return positions to update board.
     * @return List<List<Integer>> positions to update.
     */
    List<List<Integer>> getPositions();

    /**
     * Method add position to the set of positions to update board.
     * @param positionsToAdd Given positions.
     * @return True if add was successful or else return false.
     */
    Boolean addPositions(List<Integer> positionsToAdd);
}
