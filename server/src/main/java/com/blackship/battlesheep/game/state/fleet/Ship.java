package com.blackship.battlesheep.game.state.fleet;

import java.util.List;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
public class Ship {

    private final List<Integer> positions;

    public Ship(List<Integer> positions) {
        this.positions = positions;
    }

    public boolean containsPosition(Integer position) {
        return positions.contains(position);
    }

    public List<Integer> getPositions() {
        return positions;
    }
}
