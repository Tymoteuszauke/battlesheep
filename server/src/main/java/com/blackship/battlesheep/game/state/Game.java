package com.blackship.battlesheep.game.state;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 *
 * A state machine that manages the game progress. It keeps one board (in the future it will
 * keep boards for both players). It is possible to get state of the board as a String
 * and to change the state of the game. The states: starting, in progress and finished.
 */
public interface Game {
    /**
     * Returns current state of both boards as a String.
     * @return Current state of the boards.
     */
    String boardsState();

    /**
     * Returns new game state.
     * @param moveSet List of moves to be applied to the board.
     * @return Returns new game state with an updated board.
     */
    Game changeState(Supplier<List<Integer>> moveSet);
}
