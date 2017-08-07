package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.state.state.FieldState;

import java.util.Map;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 *
 * Represents the game board (10x10 size).
 * For testing purposes there is toString method which prints entire board.
 */
public interface Board {
    Map<Integer, FieldState> getPositions();

    /**
     * Returns the state of the given field.
     * @param shipPosition Board field number to be checked.
     * @return State of the field that is being checked.
     */
    FieldState getPositionState(Integer shipPosition);
}
