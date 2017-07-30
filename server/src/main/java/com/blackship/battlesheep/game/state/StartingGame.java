package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.board.Board;
import com.blackship.battlesheep.game.state.board.BoardModifier;
import com.blackship.battlesheep.game.state.board.StartingBoard;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
public class StartingGame implements Game {
    private Board playerOneBoard;

    public StartingGame() {
        this.playerOneBoard = new StartingBoard();
    }

    public StartingGame(Board playerOneBoard) {
        this.playerOneBoard = playerOneBoard;
    }

    //TODO: create a parser and forward moveSet there
    @Override
    public Game changeState(Supplier<List<Integer>> moveSet) {
        return new StartingGame(BoardModifier.insertPositions(moveSet.get()));
    }

    @Override
    public String boardsState() {
        return playerOneBoard.toString();
    }
}
