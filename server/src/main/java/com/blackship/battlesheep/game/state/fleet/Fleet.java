package com.blackship.battlesheep.game.state.fleet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anna Gawda
 * @since 31.07.2017
 */
public class Fleet {
    private List<Ship> ships;

    public Fleet(List<List<Integer>> fleetShipPositions) {
        ships = new ArrayList<>();
        for(List<Integer> shipPositions: fleetShipPositions) {
            Ship ship = new Ship(shipPositions);
            ships.add(ship);
        }
    }

    public boolean isSunk() {
        for(Ship ship: ships) {
            if(ship.isSunk()) return true;
        }
        return false;
    }
}
