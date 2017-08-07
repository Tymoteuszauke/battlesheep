package com.blackship.battlesheep.game.state;

/**
 * @author Anna Gawda
 * @since 04.08.2017
 */
public enum GameWinner {
    PLAYER_ONE("First player won"),
    PLAYER_TWO("Second player won"),
    BOTH_PLAYERS("Draw"),
    NONE("");

    private String message;

    GameWinner(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
