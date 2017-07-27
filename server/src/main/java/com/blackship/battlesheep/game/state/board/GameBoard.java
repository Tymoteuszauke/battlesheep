package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.Position;

import java.util.Map;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
public class GameBoard implements Board {

    private Map<Integer, Position> positions;

    public GameBoard(Map<Integer, Position> positions) {
        this.positions = positions;
    }

    @Override
    public String boardLayout() {
        return null;
    }

    @Override
    public Map<Integer, Position> getPositions() {
        return positions;
    }
}
