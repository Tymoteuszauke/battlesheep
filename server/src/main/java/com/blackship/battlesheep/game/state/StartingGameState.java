package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.exceptions.WrongStateException;

import java.util.List;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
public class StartingGameState implements GameState {

    @Override
    public GameState changeState(List<List<Integer>> firstPlayerPositions, List<List<Integer>> secondPlayerPositions) {
        return new GameStateInProgress(firstPlayerPositions, secondPlayerPositions);
    }

    @Override
    public List<List<Integer>> shotPositions() throws WrongStateException {
        throw new WrongStateException();
    }
}
