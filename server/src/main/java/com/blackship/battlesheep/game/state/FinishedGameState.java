package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.exceptions.WrongStateException;

import java.util.List;

/**
 * @author Anna Gawda
 * @since 04.08.2017
 */
public class FinishedGameState implements GameState {

    private String winner;

    FinishedGameState(String winner) {
        this.winner = winner;
    }

    @Override
    public GameState changeState(List<List<Integer>> firstPlayerPositions, List<List<Integer>> secondPlayerPositions) {

        return this;
    }

    @Override
    public List<List<Integer>> shotPositions() throws WrongStateException {

        throw new WrongStateException();
    }

    public String getWinner() {
        return winner;
    }
}
