package com.blackship.battlesheep.game;

import com.blackship.battlesheep.game.state.GameState;
import com.blackship.battlesheep.game.state.StartingGameState;
import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;

import java.util.List;

/**
 * @author Anna Gawda
 * @since 01.08.2017
 */
public class Game {

    private final static Logger log = LogUtils.getLogger();

    public GameState gameState;

    public Game() {
        gameState = new StartingGameState();
    }

    public void startGame(List<List<Integer>> firstPlayerShips, List<List<Integer>> secondPlayerShips) {
        gameState = gameState.changeState(firstPlayerShips, secondPlayerShips);
    }

    public List<List<Integer>> move(List<List<Integer>> firstPlayerPositions, List<List<Integer>> secondPlayerPositions) {
        gameState = gameState.changeState(firstPlayerPositions, secondPlayerPositions);
        List<List<Integer>> shotPositions = gameState.shotPositions();

        log.info(String.format("Positions to shoot from the first player: %s", firstPlayerPositions));
        log.info(String.format("Positions to shoot from the second player: %s", secondPlayerPositions));
        log.info(String.format("Positions shot: %s", shotPositions));

        return shotPositions;
    }

}
