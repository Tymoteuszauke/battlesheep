package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.Position;

import java.util.Map;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 *
 * Represents the game board (10x10 size). It makes it possible to get board state and in the future
 * - to update the board.
 * TODO getPosition(int position) method
 */
public interface Board {
    /**
     * Returns current state of the board as a String.
     *
     * @return Current board state.
     */
    String boardLayout();
    Map<Integer, Position> getPositions();
}
