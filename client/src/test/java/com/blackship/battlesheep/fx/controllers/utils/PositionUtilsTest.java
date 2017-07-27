package com.blackship.battlesheep.fx.controllers.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Mateusz Słaboński on 27.07.17
 * @project
 */
public class PositionUtilsTest {

    public static final int BOARD_SIZE = 10;

    @DataProvider
    public static Object[][] oneDimensionalPositionData() {
        return new Object[][] {
                {1, 0, 1},
                {23, 2, 3},
                {56, 5, 6},
                {100, 10, 0}
        };
    }

    @Test(dataProvider = "oneDimensionalPositionData")
    public void testCalculateFromTwoDimensionalPosition(int expectedPosition, int givenX, int givenY) throws Exception {
        //given
        //when
        int givenPosition = PositionUtils.calculateFromTwoDimensionalPosition(BOARD_SIZE, givenX, givenY);
        //then
        assertEquals(expectedPosition, givenPosition);
    }

    @Test(dataProvider = "oneDimensionalPositionData")
    public void testCalculateFromOneDimensionalPosition(int position, int expectedX, int expectedY) throws Exception {
        //given position

        //when
        int[] givenData = PositionUtils.calculateFromOneDimensionalPosition(position, BOARD_SIZE);
        //then
        assertEquals(givenData[0], expectedX);
        assertEquals(givenData[1], expectedY);
    }

}