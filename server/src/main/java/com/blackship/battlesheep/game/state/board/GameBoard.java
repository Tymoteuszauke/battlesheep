package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.Position;

import java.util.Map;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
public class GameBoard implements Board {

    private Map<Integer, Position> positions;
    private final Integer ROW_SIZE = 10;

    public GameBoard(Map<Integer, Position> positions) {
        this.positions = positions;
    }

    @Override
    public String boardLayout() {
        StringBuilder stringBuilder = new StringBuilder();
        positions.forEach((k, v) -> {
            stringBuilder.append(String.format("%2s", String.valueOf(v.getFieldState())));
            if (isNewLine(v.getPosition())) {
                stringBuilder.append(System.getProperty("line.separator"));
            }
        });

        return stringBuilder.toString();
    }

    @Override
    public Map<Integer, Position> getPositions() {
        return positions;
    }

    private boolean isNewLine(Integer x) {
        return x % ROW_SIZE == 0;
    }
}
