package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.board.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Anna Gawda
 * @since 30.07.2017
 */
class FinishedGame implements Game {
    @Override
    public Map<Integer, Board> boardsState() {
        return new HashMap<>();
    }

    @Override
    public Game changeState(Supplier<List<Integer>> moveSet) {
        return new StartingGame();
    }
}
