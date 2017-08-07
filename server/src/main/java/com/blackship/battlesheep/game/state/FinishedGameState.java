package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.exceptions.WrongStateException;
import com.blackship.battlesheep.game.state.fleet.Fleet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anna Gawda
 * @since 04.08.2017
 */
public class FinishedGameState implements GameState {

    private String winner;
    private Fleet firstPlayerFleet;
    private Fleet secondPlayerFleet;

    public FinishedGameState(String winner, Fleet firstPlayerFleet, Fleet secondPlayerFleet) {
        this.winner = winner;
        this.firstPlayerFleet = firstPlayerFleet;
        this.secondPlayerFleet = secondPlayerFleet;
    }

    @Override
    public GameState changeState(List<List<Integer>> firstPlayerPositions, List<List<Integer>> secondPlayerPositions) {
        return this;
    }

    @Override
    public List<List<Integer>> shotPositions() {
        List<List<Integer>> positions = new ArrayList<>();
        positions.add(0, firstPlayerFleet.getShotPositions());
        positions.add(1, secondPlayerFleet.getShotPositions());

        return positions;
    }

    public String getWinner() {
        return winner;
    }
}
