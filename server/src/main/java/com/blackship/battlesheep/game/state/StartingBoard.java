package com.blackship.battlesheep.game.state;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
public class StartingBoard implements Board {
    private List<Integer> positions;
    private static final Integer BOARD_SIZE = 100;
    private static final Integer ROW_SIZE = 10;
    private static final Integer BOARD_FIRST_FIELD = 1;

    public StartingBoard() {
        this.positions = new ArrayList<>(BOARD_SIZE);
        fillStartingPositions();
    }

    @Override
    public String boardLayout() {
        StringBuilder stringBuilder = new StringBuilder();
        positions.forEach(x -> {
            stringBuilder.append(String.format("%4s", String.valueOf(x)));
            if (x % ROW_SIZE == 0) {
                stringBuilder.append(System.getProperty("line.separator"));
            }
        });
        return stringBuilder.toString();
    }

    private void fillStartingPositions() {
        IntStream.rangeClosed(BOARD_FIRST_FIELD, BOARD_SIZE)
                .forEach(x -> positions.add(x));
    }
}
