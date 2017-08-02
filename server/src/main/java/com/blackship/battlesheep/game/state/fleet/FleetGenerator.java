package com.blackship.battlesheep.game.state.fleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Anna Gawda
 * @since 02.08.2017
 */
public class FleetGenerator {

    private FleetGenerator() {}

    public static Fleet hardcodeShipsToFleet() {
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

        return new Fleet(ships);
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
