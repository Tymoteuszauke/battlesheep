package com.blackship.battlesheep.fx;

import com.blackship.battlesheep.fx.utils.ButtonUtils;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;

import java.util.*;
import java.util.stream.Collectors;

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
        List<Integer> enemyHitPositions = salvoHandler.getEnemyHitPositions();
        updateEnemyBoard(enemyHitPositions);
        updatePlayerBoard(salvoHandler.getPlayerHitPositions());
        enemyMastPositions
                .stream()
                .filter(data -> !data.getText().equals("X"))
                .forEach(data -> {
                    data.setDisable(false);
                    data.setStyle(ButtonUtils.defaultButtonColorStyle());
                });
    }

    public void feedCannon(int cannonBall) {
        loadedCannons.add(cannonBall);
        long availableCannons = playerMastPositions
                .stream()
                .filter(data -> !data.isDisabled())
                .count();

        if (loadedCannons.size() == availableCannons) {
            fireCannons(loadedCannons);
        }
    }

    private void fireCannons(List<Integer> cannons) {
        salvoHandler.fireSalvoCannonade(cannons);
        loadedCannons.clear();
        boardViewUpdaterListener.update("...!!!!FIRE!!!!...\n");
    }
    //TODO update to fix
    private void updatePlayerBoard(List<Integer> positions) {
        positions.forEach(data -> {
            Button playerMast = playerMastPositions
                    .stream()
                    .filter(button -> button.getId().equals(String.valueOf(data)))
                    .findAny()
                    .get();
            playerMast.setText("X");
            playerMast.setDisable(true);
        });
    }
    //TODO update to fix
    private void updateEnemyBoard(List<Integer> positions) {
        positions.forEach(data -> {
            Button enemyMast = enemyMastPositions
                    .stream()
                    .filter(button -> button.getId().equals(String.valueOf(data)))
                    .findAny()
                    .get();
            enemyMast.setText("X");
            enemyMast.setDisable(true);
        });
    }
}
