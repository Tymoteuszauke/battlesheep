package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.exceptions.WrongStateException;

import java.util.List;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 *
 * A state machine that manages the game progress. It keeps the fleets of both players. It allows the server
 * to make a move and to monitor the game state which can be Starting and InProgress. Making moves is only
 * possible in the InProgress state.
 */
public interface GameState {
    int NUMBER_OF_PLAYERS = 2;

    /**
     *
     * @return Returns new game state with an updated board.
     */
    GameState changeState(List<List<Integer>> firstPlayerPositions, List<List<Integer>> secondPlayerPositions);

    //TODO: change list of integers into an object
    List<List<Integer>> shotPositions() throws WrongStateException;
}
