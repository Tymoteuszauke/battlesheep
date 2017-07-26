package com.blackship.battlesheep.game;

/**
 * @author Anna Gawda
 * @since 26.07.17
 *
 * Keeps state of the boards for both players. It returns the state and in the future
 * it will also update it.
 */
public interface Game {
    /**
     * Returns current state of both boards as a String.
     *
     * @return Current state of the boards.
     */
    String boardsState();
}
