package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.exceptions.FirstPlayerWon;
import com.blackship.battlesheep.game.state.exceptions.SecondPlayerWon;

import java.util.List;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 *
 * A state machine that manages the game progress. It keeps one board (in the future it will
 * keep boards for both players). It is possible to get state of the board as a String
 * and to change the state of the game. The states: starting, in progress and finished.
 */
public interface GameState {
    int NUMBER_OF_PLAYERS = 2;

    /**
     * @return Returns new game state with an updated board.
     */
    GameState changeState(List<List<Integer>> firstPlayerPositions, List<List<Integer>> secondPlayerPositions)
            throws FirstPlayerWon, SecondPlayerWon;

    //TODO: change list of integers into an object
    List<List<Integer>> shotPositions();
}
