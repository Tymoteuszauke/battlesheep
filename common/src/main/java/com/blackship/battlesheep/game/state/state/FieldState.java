package com.blackship.battlesheep.game.state.state;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
public enum FieldState {
    EMPTY("O"),
    SHOT("+"),
    SUNK("X"),
    TAKEN("-");

    private String symbol;

    FieldState(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
