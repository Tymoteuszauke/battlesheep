package com.blackship.battlesheep.fx.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Mateusz Słaboński
 * @since 26.07.2017
 */
public class PositionUtilsTest {

    private static final int BOARD_SIZE = 10;

    @DataProvider
    public static Object[][] oneDimensionalPositionData() {
        return new Object[][] {
                {1, 1, 0},
                {23, 3, 2},
                {56, 6, 5},
                {100, 0, 10}
        };
    }

    @Test(dataProvider = "oneDimensionalPositionData")
    public void shouldReturnPositionFromCoordinates(int expectedPosition, int givenX, int givenY) throws Exception {
        //given - expectedPosition, givenX, givenY

        //when
        int givenPosition = PositionUtils.calculateFromTwoDimensionalPosition(BOARD_SIZE, givenX, givenY);

        //then
        assertEquals(expectedPosition, givenPosition);
    }

    @Test(dataProvider = "oneDimensionalPositionData")
    public void shouldReturnCoordinatesFromPosition(int position, int expectedX, int expectedY) throws Exception {
        //given - position, expectedX, expectedY

        //when
        int[] givenData = PositionUtils.calculateFromOneDimensionalPosition(position, BOARD_SIZE);

        //then
        assertEquals(givenData[0], expectedX);
        assertEquals(givenData[1], expectedY);
    }
}