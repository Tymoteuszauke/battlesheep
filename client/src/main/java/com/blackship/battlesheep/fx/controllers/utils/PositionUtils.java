package com.blackship.battlesheep.fx.controllers.utils;

/**
 * @author Mateusz Słaboński on 26.07.17
 * @project
 */
public class PositionUtils {

    public static int calculateTwoDimensionalPosition(int columns, int column, int row) {
        return row * columns + column;
    }

    public static int[] calculateOneDimensionalPosition(int position, int boardSize) {
        int x = position % boardSize;
        int y = position / boardSize;
        return new int[] {x, y};
    }
}
