package com.blackship.battlesheep.game.state.fleet;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Anna Gawda
 * @since 31.07.2017
 */
@Test
public class ShipTest {
    @DataProvider
    private Object[][] shipCreationData() {
        return new Object[][] {
                {new ArrayList<>(Arrays.asList(1, 2, 3, 4))},
                {new ArrayList<>(Arrays.asList( 22, 23, 24))},
                {new ArrayList<>(Arrays.asList(23, 24, 34))},
                {new ArrayList<>(Arrays.asList(5))},
        };
    }

    @Test(dataProvider = "shipCreationData")
    public void shouldReturnTrueForEachPosition(List<Integer> shipPositions) {
        //given
        Ship ship = new Ship(shipPositions);

        //then
        for(Integer position: shipPositions) {
            assertTrue(ship.containsPosition(position));
        }
    }

    @Test(dataProvider = "shipCreationData")
    public void shouldReturnFalseForAliveShip(List<Integer> shipPositions) {
        //given
        Ship ship = new Ship(shipPositions);

        //then
        assertFalse(ship.isSunk());
    }
}