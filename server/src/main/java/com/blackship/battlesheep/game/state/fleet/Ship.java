package com.blackship.battlesheep.game.state.fleet;

import com.blackship.battlesheep.game.state.state.FieldState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
public class Ship {

    private final Map<Integer, FieldState> shipPositions;

    public Ship(List<Integer> positions) {
        shipPositions = new HashMap<>();
        positions.forEach(position -> shipPositions.put(position, FieldState.TAKEN));
    }

    public boolean containsPosition(Integer position) {
        return shipPositions.containsKey(position);
    }

    //TODO: it will return true if all of the states in shipPositions will be FieldState.SUNK
    public boolean isSunk() {
        return false;
    }
}
