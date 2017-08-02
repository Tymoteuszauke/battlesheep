package com.blackship.battlesheep.game.state.fleet;

import com.blackship.battlesheep.game.state.TestUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * @author Anna Gawda
 * @since 31.07.2017
 */
@Test
public class FleetTest {

    @DataProvider
    private Object[][] shipCreationDataWithExpectationsOfSunk() {
        return new Object[][] {
                {Arrays.asList(1, 2), Arrays.asList(1, 2), true},
                {Arrays.asList(1, 2), Arrays.asList(), false}
        };
    }

    @Test(dataProvider = "shipCreationDataWithExpectationsOfSunk")
    public void shouldReturnFalseForAliveShip(
            List<Integer> givenShipPositions,
            List<Integer> givenShotPositions,
            boolean expectedSunkResult) {
        //given
        Fleet givenFleet = new Fleet(Arrays.asList(givenShipPositions));

        //when
        givenFleet.shootShipPositions(givenShotPositions);

        //then
        assertEquals(givenFleet.getShotPositions(), givenShotPositions);
        assertEquals(givenFleet.isSunk(), expectedSunkResult);
    }

}