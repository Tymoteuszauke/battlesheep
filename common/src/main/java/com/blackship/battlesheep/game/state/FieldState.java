package com.blackship.battlesheep.game.state;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
public enum FieldState {
    EMPTY("O"),
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
