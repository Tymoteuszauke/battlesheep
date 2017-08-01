package com.blackship.battlesheep.game.state.board;

/**
 * @author Anna Gawda
 * @since 01.08.2017
 */
public enum BoardSettings {
    BOARD_SIZE(100),
    BOARD_STARTING_FIELD(1),
    BOARD_WIDTH(10);

    private int value;

    BoardSettings(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
