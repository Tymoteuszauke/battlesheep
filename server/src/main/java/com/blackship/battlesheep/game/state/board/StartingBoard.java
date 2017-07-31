package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.state.state.FieldState;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */

//TODO: remove starting and game boards - have just one board, introduce states instead
public class StartingBoard implements Board {
    private final Map<Integer, FieldState> positions;
    private final Integer BOARD_SIZE = 100;
    private final Integer ROW_SIZE = 10;
    private final Integer BOARD_FIRST_FIELD = 1;

    public StartingBoard() {
        this.positions = new HashMap<>(BOARD_SIZE);
        fillStartingPositions();
    }

    public StartingBoard(Map<Integer, FieldState> boardPositions) {
        this.positions = boardPositions;
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
        return Collections.unmodifiableMap(positions);
    }

    @Override
    public FieldState getPositionState(Integer shipPosition) {
        return positions.get(shipPosition);
    }

    private boolean isNewLine(Integer x) {
        return x % ROW_SIZE == 0;
    }

    private void fillStartingPositions() {
        IntStream.rangeClosed(BOARD_FIRST_FIELD, BOARD_SIZE)
                .forEach(position -> positions.put(position, FieldState.EMPTY));
    }
}
