package com.blackship.battlesheep.game.state.fleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Integer> getShotPositions() {
        return ships.stream()
                .flatMap(ship -> ship.getShotPositions().stream())
                .collect(Collectors.toList());
    }

    public void shootShipPositions(List<Integer> shipPositions) {
        for(Ship ship: ships) {
            for(Integer position: shipPositions) {
                if (ship.containsPosition(position)) {
                    ship.setShotPosition(position);
                }
            }
        }
    }
}
