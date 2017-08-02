package com.blackship.battlesheep.game.state.fleet;

import com.blackship.battlesheep.game.state.state.FieldState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
public class Ship {

    private final Map<Integer, FieldState> shipPositions;
    private boolean isSunk;

    public Ship(List<Integer> positions) {
        shipPositions = new HashMap<>();
        positions.forEach(position -> shipPositions.put(position, FieldState.TAKEN));
        isSunk = false;
    }

    public boolean containsPosition(Integer position) {
        return shipPositions.containsKey(position);
    }

    public boolean isSunk() {
        return isSunk;
    }

    public List<Integer> getShotPositions() {
        return shipPositions.keySet().stream()
                .filter(position ->
                        shipPositions.get(position) == FieldState.SHOT ||
                        shipPositions.get(position) == FieldState.SUNK
                        )
                .collect(Collectors.toList());
    }

    public void setShotPosition(Integer shotPosition) {
        shipPositions.replace(shotPosition, FieldState.SHOT);
        isSunk = isShipSunkAfterShooting();
    }

    private boolean isShipSunkAfterShooting() {
        boolean sunk = shipPositions.entrySet().stream().allMatch(fieldState -> fieldState.getValue() == FieldState.SHOT);
        if (sunk) shipPositions.forEach((position, fieldState) -> shipPositions.replace(position, FieldState.SUNK));
        return sunk;
    }
}
