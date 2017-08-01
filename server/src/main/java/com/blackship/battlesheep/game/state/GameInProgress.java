package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.board.Board;
import com.blackship.battlesheep.game.state.board.BoardModifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Anna Gawda
 * @since 30.07.2017
 * This class is a stub for the state machine prototype, will be expanded later.
 */
class GameInProgress implements Game {
    //TODO: switch to PlayerSettings
    private Board playerOneBoard;
    private Board playerTwoBoard;

    GameInProgress(Board playerOneBoard, Board playerTwoBoard) {
        this.playerOneBoard = playerOneBoard;
        this.playerTwoBoard = playerTwoBoard;
    }

    @Override
    public Map<Integer, Board> boardsState() {
        return new HashMap<>();
    }

    @Override
    public Game changeState(Supplier<List<Integer>> moveSet) {
        List<Integer> moves = moveSet.get();

        if (moves.isEmpty()) return new FinishedGame();

        return new GameInProgress(BoardModifier.insertPositions(moves), BoardModifier.insertPositions(moves));
    }
}
