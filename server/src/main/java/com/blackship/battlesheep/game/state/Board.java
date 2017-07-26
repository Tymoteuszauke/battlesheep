package com.blackship.battlesheep.game.state;

/**
 * @author Anna Gawda
 * @since 26.07.17
 *
 * Represents the game board (10x10 size). It makes it possible to get board state and in the future
 * - to update the board.
 */
public interface Board {
    /**
     * Returns current state of the board as a String.
     *
     * @return Current board state.
     */
    String boardLayout();
}
