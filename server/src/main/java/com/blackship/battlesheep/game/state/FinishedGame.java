package com.blackship.battlesheep.game.state;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Anna Gawda
 * @since 30.07.2017
 */
public class FinishedGame implements Game {
    @Override
    public String boardsState() {
        return "Game finished";
    }

    @Override
    public Game changeState(Supplier<List<Integer>> moveSet) {
        return new StartingGame();
    }
}
