package com.blackship.battlesheep.game.state.fleet;

import java.util.List;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * @author Anna Gawda
 * @since 06.08.2017
 */
@Test
public class FleetGeneratorTest {

    @Test
    public void shouldReturn10ShipFleet() {
        List<List<Integer>> givenFleet;

        for(int i = 0; i < 100; i++) {
            givenFleet = new FleetGenerator().generateRandomFleet();

            assertEquals(givenFleet.size(), 10);
        }
    }

    @Test
    public void shouldReturnDistinctPositions() {
        List<List<Integer>> givenFleet;

        for(int i = 0; i < 100; i++) {
            givenFleet = new FleetGenerator().generateRandomFleet();

            assertEquals(givenFleet.stream().flatMap(List::stream).distinct().count(), 20);
        }
    }
}