package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.state.FieldState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
public class TestUtils {
    private static final Integer BOARD_SIZE = 100;
    private static final Integer ROW_SIZE = 10;
    private static final Integer BOARD_FIRST_FIELD = 1;

    private TestUtils() {
    }

    public static String generateBoardState(FieldState state) {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.rangeClosed(BOARD_FIRST_FIELD, BOARD_SIZE).forEach(x -> {
            stringBuilder.append(String.format("%2s", state));
            if (x % ROW_SIZE == 0) stringBuilder.append(System.getProperty("line.separator"));
        });

        return stringBuilder.toString();
    }

    public static List<Integer> generateListWithNumbers() {
        List<Integer> result = new ArrayList<>();
        IntStream.rangeClosed(1, 100).forEach(result::add);
        return result;
    }

    public static List<List<Integer>> hardcodeShips() {
        List<List<Integer>> ships = new ArrayList<>();
        ships.add(Arrays.asList(12, 22, 32, 42));
        ships.add(Arrays.asList(14, 15, 16));
        ships.add(Arrays.asList(37, 47, 57));
        ships.add(Arrays.asList(72, 73));
        ships.add(Arrays.asList(76, 77));
        ships.add(Arrays.asList(99, 100));
        ships.add(Arrays.asList(19));
        ships.add(Arrays.asList(54));
        ships.add(Arrays.asList(90));
        ships.add(Arrays.asList(94));

        return ships;
    }
}
