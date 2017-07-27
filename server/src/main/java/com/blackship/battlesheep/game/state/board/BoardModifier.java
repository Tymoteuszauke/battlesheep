package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.FieldState;
import com.blackship.battlesheep.game.Position;
import com.blackship.battlesheep.game.state.fleet.Ship;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
public class BoardModifier {

    private BoardModifier() {}



    public static Board insertShip(Board board, List<Integer> shipPositions) {
        Map<Integer, Position> boardPositions = new HashMap<>(board.getPositions());
        shipPositions.forEach(data -> boardPositions.replace(data, new Position(data, FieldState.TAKEN)));
        return new GameBoard(boardPositions);
    }

    public static Board insertShips(Board board, List<Ship> ships) {
        Board refreshedBoard = board;
        for (Ship ship : ships) {
            refreshedBoard = insertShip(refreshedBoard, ship.getPositions());
        }
        return refreshedBoard;
    }
}
