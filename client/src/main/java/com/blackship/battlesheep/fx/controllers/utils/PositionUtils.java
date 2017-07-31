package com.blackship.battlesheep.fx.controllers.utils;

/**
 * @author Mateusz Słaboński
 * @since 26.07.2017
 */
public class PositionUtils {

    public static int calculateFromTwoDimensionalPosition(int columns, int column, int row) {
        return row * columns + column;
    }

    public static int[] calculateFromOneDimensionalPosition(int position, int boardSize) {
        int x = position % boardSize;
        int y = position / boardSize;
        return new int[] {x, y};
    }
}
