package com.blackship.battlesheep.game;

import com.blackship.battlesheep.game.state.FieldState;

import java.util.stream.IntStream;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
public class TestUtils {
    private static final Integer BOARD_SIZE = 100;
    private static final Integer ROW_SIZE = 10;
    private static final Integer BOARD_FIRST_FIELD = 1;

    private TestUtils() {}

    public static String generateEmptyBoardState() {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.rangeClosed(BOARD_FIRST_FIELD, BOARD_SIZE).forEach(x -> {
            stringBuilder.append(String.format("%2s", FieldState.EMPTY));
            if (x % ROW_SIZE == 0) stringBuilder.append(System.getProperty("line.separator"));
        });

        return stringBuilder.toString();
    }
}
