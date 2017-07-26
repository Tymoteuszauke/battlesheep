package com.blackship.battlesheep.game;

import com.blackship.battlesheep.game.state.Board;
import com.blackship.battlesheep.game.state.StartingBoard;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
public class GameManager implements Game {
    private Board board;

    public GameManager() {
        this.board = new StartingBoard();
    }

    //TODO: refactor
    @Override
    public String boardsState() {
        return board.boardLayout();
    }
}
