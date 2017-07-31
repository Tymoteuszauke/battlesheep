package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.state.state.FieldState;
import com.blackship.battlesheep.game.state.fleet.Ship;

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


    public static Board insertShips(List<Ship> ships) {
        Map<Integer, FieldState> insertShips = new HashMap<>();
        for (Ship ship : ships) {
            for (Integer i : ship.getPositions()) {
                insertShips.put(i, FieldState.TAKEN);
            }
        }
        IntStream.rangeClosed(1, 100).forEach(i -> insertShips.putIfAbsent(i, FieldState.EMPTY));

        return new StartingBoard(insertShips);
    }

    public static Board insertPositions(List<Integer> positions) {
        Map<Integer, FieldState> insertShips = new HashMap<>();
        for (Integer i : positions) {
            insertShips.put(i, FieldState.TAKEN);
        }
        IntStream.rangeClosed(1, 100).forEach(i -> insertShips.putIfAbsent(i, FieldState.EMPTY));

        return new StartingBoard(insertShips);
    }
}
