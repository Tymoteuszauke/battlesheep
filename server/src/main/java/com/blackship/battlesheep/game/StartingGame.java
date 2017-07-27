package com.blackship.battlesheep.game;

import com.blackship.battlesheep.game.state.board.Board;
import com.blackship.battlesheep.game.state.board.StartingBoard;
import com.blackship.battlesheep.game.state.fleet.Ship;

import java.util.List;
import java.util.function.Function;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
public class StartingGame implements Game {
    private Board playerOneBoard;
    private Function<List<Ship>, Board> insertShips;

    public StartingGame() {
        this.playerOneBoard = new StartingBoard();
    }
    //TODO: these methods are not used, but I need them here
    public StartingGame(Function<List<Ship>, Board> insertShips) {
        this.insertShips = insertShips;
    }

    public void fillBoard(List<Ship> ships) {
        this.playerOneBoard = insertShips.apply(ships);
    }

    @Override
    public String boardsState() {
        return playerOneBoard.boardLayout();
    }
}
