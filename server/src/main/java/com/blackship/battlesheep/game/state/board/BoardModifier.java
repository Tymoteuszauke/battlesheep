package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.state.FieldState;
import com.blackship.battlesheep.game.Position;
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
        Map<Integer, Position> boardPositions = new HashMap<>(board.getPositions());
        shipPositions.forEach(data -> boardPositions.replace(data, new Position(data, FieldState.TAKEN)));
        return new GameBoard(boardPositions);
    }

    public static Board insertShips(List<Ship> ships) {
        Map<Integer, Position> insertShips = new HashMap<>();
        for (Ship ship : ships) {
            for (Integer i : ship.getPositions()) {
                insertShips.put(i, new Position(i, FieldState.TAKEN));
            }
        }

        IntStream.rangeClosed(1, 100).forEach(i -> insertShips.putIfAbsent(i, new Position(i)));
        return new GameBoard(insertShips);
    }
}
