package com.blackship.battlesheep.fx.utils;

/**
 * @author Mateusz Słaboński
 * @since 26.07.2017
 */
public class PositionUtils {

    private PositionUtils() {}

    public static int calculateFromTwoDimensionalPosition(int columns, int column, int row) {
        return row * columns + column;
    }

    public static int[] calculateFromOneDimensionalPosition(int position, int boardSize) {
        int x = position % boardSize;
        int y = position / boardSize;
        return new int[] {x, y};
    }
}
