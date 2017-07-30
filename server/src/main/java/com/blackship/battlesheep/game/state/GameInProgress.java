package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.board.Board;
import com.blackship.battlesheep.game.state.board.BoardModifier;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Anna Gawda
 * @since 30.07.2017
 */
public class GameInProgress implements Game {
    private Board playerOneBoard;

    public GameInProgress(Board playerOneBoard) {
        this.playerOneBoard = playerOneBoard;
    }

    @Override
    public String boardsState() {
        return playerOneBoard.toString();
    }

    @Override
    public Game changeState(Supplier<List<Integer>> moveSet) {
        List<Integer> moves = moveSet.get();

        if (moves.isEmpty()) return new FinishedGame();

        return new GameInProgress(BoardModifier.insertPositions(moves));
    }
}
