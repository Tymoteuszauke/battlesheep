package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.state.FieldState;

import java.util.Map;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
public class GameBoard implements Board {

    private Map<Integer, FieldState> positions;
    private final Integer ROW_SIZE = 10;

    public GameBoard(Map<Integer, FieldState> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        positions.forEach((k, v) -> {
            stringBuilder.append(String.format("%2s", v));
            if (isNewLine(k)) {
                stringBuilder.append(System.getProperty("line.separator"));
            }
        });

        return stringBuilder.toString();
    }

    @Override
    public Map<Integer, FieldState> getPositions() {
        return positions;
    }

    @Override
    public FieldState getPositionState(Integer shipPosition) {
        return positions.get(shipPosition);
    }

    private boolean isNewLine(Integer x) {
        return x % ROW_SIZE == 0;
    }
}
