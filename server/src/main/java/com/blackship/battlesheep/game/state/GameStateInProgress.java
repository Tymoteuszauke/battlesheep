package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.fleet.Fleet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anna Gawda
 * @since 30.07.2017
 * This class is a stub for the state machine prototype, will be expanded later.
 */
public class GameStateInProgress implements GameState {

    private Fleet firstPlayerFleet;
    private Fleet secondPlayerFleet;

    public GameStateInProgress(List<List<Integer>> firstPlayerPositions, List<List<Integer>> secondPlayerPositions) {
        firstPlayerFleet = new Fleet(firstPlayerPositions);
        secondPlayerFleet = new Fleet(secondPlayerPositions);
    }

    @Override
    public GameState changeState(List<List<Integer>> firstPlayerPositions, List<List<Integer>> secondPlayerPositions) {
        List<Integer> firstPlayerMoves = firstPlayerPositions.get(0);
        List<Integer> secondPlayerMoves = secondPlayerPositions.get(0);
        firstPlayerFleet.shootShipPositions(firstPlayerMoves);
        secondPlayerFleet.shootShipPositions(secondPlayerMoves);
        return this;
    }

    @Override
    public List<List<Integer>> shotPositions() {
        List<List<Integer>> shotPositions = new ArrayList<>(NUMBER_OF_PLAYERS);
        shotPositions.add(firstPlayerFleet.getShotPositions());
        shotPositions.add(secondPlayerFleet.getShotPositions());
        return shotPositions;
    }
}
