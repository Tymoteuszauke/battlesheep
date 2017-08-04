package com.blackship.battlesheep.game;

import com.blackship.battlesheep.game.state.GameState;
import com.blackship.battlesheep.game.state.StartingGameState;
import com.blackship.battlesheep.game.state.exceptions.WrongStateException;
import com.blackship.battlesheep.game.state.fleet.FleetGenerator;
import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Anna Gawda
 * @since 01.08.2017
 */
public class Game {

    private final static Logger log = LogUtils.getLogger();

    private GameState gameState;

    public Game() {
        gameState = new StartingGameState();
    }

    public void startGame(List<List<Integer>> firstPlayerShips, List<List<Integer>> secondPlayerShips) {
        gameState = gameState.changeState(firstPlayerShips, secondPlayerShips);
    }

    public List<List<Integer>> move(List<List<Integer>> firstPlayerPositions, List<List<Integer>> secondPlayerPositions)
            throws WrongStateException {
        gameState = gameState.changeState(firstPlayerPositions, secondPlayerPositions);
        List<List<Integer>> shotPositions = gameState.shotPositions();

        log.info(String.format("Positions to shoot from the first player: %s", firstPlayerPositions));
        log.info(String.format("Positions to shoot from the second player: %s", secondPlayerPositions));
        log.info(String.format("Positions shot: %s", shotPositions));

        return shotPositions;
    }

    public static void main(String[] args) throws WrongStateException {

        List<List<Integer>> abc = FleetGenerator.hardcodeShips();

        Game game = new Game();
        game.startGame(abc, abc);

        List<List<Integer>> moves = new ArrayList<>();
        moves.add(Arrays.asList(12, 14));
        game.move(moves, moves);
        moves = new ArrayList<>();
        moves.add(Arrays.asList(100));
        game.move(moves, moves);
    }
}
