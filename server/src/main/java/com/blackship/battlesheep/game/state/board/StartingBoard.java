package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
public class StartingBoard implements Board {
    private final Map<Integer, Position> positions;
    private final Integer BOARD_SIZE = 100;
    private final Integer ROW_SIZE = 10;
    private final Integer BOARD_FIRST_FIELD = 1;

    public StartingBoard() {
        this.positions = new HashMap<>(BOARD_SIZE);
        fillStartingPositions();
    }

    @Override
    public String boardLayout() {
        StringBuilder stringBuilder = new StringBuilder();
        positions.forEach((k, v) -> {
            stringBuilder.append(String.format("%4s", String.valueOf(v.getPosition())));
            if (isNewLine(v.getPosition())) {
                stringBuilder.append(System.getProperty("line.separator"));
            }
        });

        return stringBuilder.toString();
    }

    @Override
    public Map<Integer, Position> getPositions() {
        return Collections.unmodifiableMap(positions);
    }

    private boolean isNewLine(Integer x) {
        return x % ROW_SIZE == 0;
    }

    private void fillStartingPositions() {
        IntStream.rangeClosed(BOARD_FIRST_FIELD, BOARD_SIZE)
                .forEach(x -> positions.put(x, new Position(x)));
    }
}
