package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.board.Board;
import com.blackship.battlesheep.game.state.board.BoardModifier;
import com.blackship.battlesheep.game.state.board.StartingBoard;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
public class StartingGame implements Game {
    //TODO: switch to PlayerSettings
    private Map<Integer, Board> playerBoards;

    public StartingGame() {
        this.playerBoards = new HashMap<>();
        IntStream.rangeClosed(1, NUMBER_OF_PLAYERS)
                .forEach(player -> playerBoards.put(player, new StartingBoard()));
    }

    public StartingGame(Board playerOneBoard) {
        this.playerBoards = new HashMap<>();
        IntStream.rangeClosed(1, NUMBER_OF_PLAYERS)
                .forEach(player -> playerBoards.put(player, playerOneBoard));
    }

    //TODO: create a parser and forward moveSet there
    @Override
    public Game changeState(Supplier<List<Integer>> moveSet) {
        return new GameInProgress(BoardModifier.insertPositions(moveSet.get()), BoardModifier.insertPositions(moveSet.get()));
    }

    @Override
    public Map<Integer, Board> boardsState() {
        return Collections.unmodifiableMap(playerBoards);
    }
}
