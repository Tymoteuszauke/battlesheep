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

public class StartingBoard implements Board {
    private final Map<Integer, FieldState> positions;

    public StartingBoard() {
        this.positions = new HashMap<>(BoardSettings.BOARD_SIZE.getValue());
        fillStartingPositions();
    }

    public StartingBoard(Map<Integer, FieldState> boardPositions) {
        this.positions = boardPositions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        positions.forEach((position, state) -> {
            stringBuilder.append(String.format("%2s", state));
            if (isNewLine(position)) {
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

    @Override
    public boolean isEmpty() {
        return positions.isEmpty();
    }

    private boolean isNewLine(Integer position) {
        return position % BoardSettings.BOARD_WIDTH.getValue() == 0;
    }

    private void fillStartingPositions() {
        IntStream.rangeClosed(BoardSettings.BOARD_STARTING_FIELD.getValue(), BoardSettings.BOARD_SIZE.getValue())
                .forEach(position -> positions.put(position, FieldState.EMPTY));
    }
}
