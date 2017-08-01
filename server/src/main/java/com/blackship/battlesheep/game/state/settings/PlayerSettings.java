package com.blackship.battlesheep.game.state.settings;

import com.blackship.battlesheep.game.state.board.Board;
import com.blackship.battlesheep.game.state.fleet.Fleet;

/**
 * @author Anna Gawda
 * @since 31.07.2017
 */
public class PlayerSettings {
    private Board board;
    private Fleet fleet;

    public PlayerSettings(Board board, Fleet fleet) {
        this.board = board;
        this.fleet = fleet;
    }

    public boolean isBoardEmpty() {
        return board.isEmpty();
    }

    public boolean isFleetSunk() {
        return fleet.isSunk();
    }
}
