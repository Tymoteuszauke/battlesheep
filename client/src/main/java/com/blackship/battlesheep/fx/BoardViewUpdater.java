package com.blackship.battlesheep.fx;

import com.blackship.battlesheep.fx.controllers.SalvoHandler;
import com.blackship.battlesheep.fx.utils.ButtonUtils;
import javafx.scene.control.Button;

import java.util.*;

/**
 * @author Mateusz Słaboński
 * @since 01.08.2017
 */
public class BoardViewUpdater implements Observer {

    private BoardViewUpdaterListener boardViewUpdaterListener;
    private List<Button> enemyMastPositions;
    private List<Button> playerMastPositions;
    private SalvoHandler salvoHandler;

    private List<Integer> loadedCannons;

    private BoardViewUpdater() {
        loadedCannons = new LinkedList<>();
    }

    public static class BoardViewUpdaterBuilder {
        BoardViewUpdater boardViewUpdater = new BoardViewUpdater();

        public BoardViewUpdaterBuilder boardViewUpdaterListener(BoardViewUpdaterListener boardViewUpdaterListener) {
            boardViewUpdater.boardViewUpdaterListener = boardViewUpdaterListener;
            return this;
        }

        public BoardViewUpdaterBuilder enemyMastPositions(List<Button> enemyMastPositions) {
            boardViewUpdater.enemyMastPositions = enemyMastPositions;
            return this;
        }

        public BoardViewUpdaterBuilder playerMastPositions(List<Button> playerMastButtons) {
            boardViewUpdater.playerMastPositions = playerMastButtons;
            return this;
        }

        public BoardViewUpdaterBuilder salvoHandler(SalvoHandler salvoHandler) {
            boardViewUpdater.salvoHandler = salvoHandler;
            salvoHandler.addObserver(boardViewUpdater);
            return this;
        }

        public BoardViewUpdater build() {
            return boardViewUpdater;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        salvoHandler = (SalvoHandler) o;

        updateEnemyBoard(salvoHandler.getEnemyHitPositions());
        updatePlayerBoard(salvoHandler.getPlayerHitPositions());
        enemyMastPositions
                .stream()
                .filter(data -> !data.isDisabled())
                .forEach(data -> data.setStyle(ButtonUtils.defaultButtonColorStyle()));
    }

    public void feedCannon(int cannonBall) {
        loadedCannons.add(cannonBall);
        if (loadedCannons.size() == playerMastPositions.size()) {
            salvoHandler.fireSalvoCannonade(loadedCannons);
            loadedCannons.clear();
            boardViewUpdaterListener.update("...!!!!FIRE!!!!...\n");
        }
    }
    //TODO Solve magic number bug(?)
    private void updatePlayerBoard(Set<Integer> positions) {
        positions.forEach(data -> {
            Button playerMast = playerMastPositions.get(data - 1);
            playerMastPositions.remove(data - 1);
            playerMast.setText("X");
            playerMast.setDisable(true);
        });
    }

    //TODO Solve magic number bug(?)
    private void updateEnemyBoard(Set<Integer> positions) {
        positions.forEach(data -> {
            Button enemyMast = enemyMastPositions.get(data - 1);
            enemyMast.setText("X");
            enemyMast.setDisable(true);
        });
    }
}
