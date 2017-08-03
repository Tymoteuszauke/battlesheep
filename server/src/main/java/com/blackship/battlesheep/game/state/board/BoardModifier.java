package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.state.state.FieldState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
public class BoardModifier {

    private BoardModifier() {
    }

    public static Board insertShip(Board board, List<Integer> shipPositions) {
        Map<Integer, FieldState> boardPositions = new HashMap<>(board.getPositions());
        shipPositions.forEach(data -> boardPositions.replace(data, FieldState.TAKEN));
        return new StartingBoard(boardPositions);
    }

    public static Board initialBoard(List<Integer> positions) {
        Map<Integer, FieldState> insertShips = new HashMap<>();
        for (Integer i : positions) {
            insertShips.put(i, FieldState.TAKEN);
        }
        IntStream.rangeClosed(BoardSettings.BOARD_STARTING_FIELD.getValue(), BoardSettings.BOARD_SIZE.getValue())
                .forEach(i -> insertShips.putIfAbsent(i, FieldState.EMPTY));

        return new StartingBoard(insertShips);
    }
}
