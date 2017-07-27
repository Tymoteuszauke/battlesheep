package com.blackship.battlesheep.game;

import com.blackship.battlesheep.game.state.board.Board;
import com.blackship.battlesheep.game.state.board.StartingBoard;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
public class GameManager implements Game {
    private Board playerOneBoard;
    private Board playerTwoBoard;

    public GameManager() {
        this.playerOneBoard = new StartingBoard();
        this.playerTwoBoard = new StartingBoard();
    }

    @Override
    public String boardsState() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Player one: ");
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append(playerOneBoard.boardLayout());
        stringBuilder.append("Player two: ");
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append(playerTwoBoard.boardLayout());
        return stringBuilder.toString();
    }
}
