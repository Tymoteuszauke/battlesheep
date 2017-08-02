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
    private int tempCannonsAmount = 4;
    private SalvoHandler salvoHandler;

    private List<Integer> currentSalvo;

    private BoardViewUpdater() {
        currentSalvo = new LinkedList<>();
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
//        updatePlayerBoard(salvoHandler.getPlayerHitPositions());
        enemyMastPositions
                .stream()
                .filter(data -> !data.isDisabled())
                .forEach(data -> data.setStyle(ButtonUtils.defaultButtonColorStyle()));
    }

    public void feedCannon(int cannonBall) {
        currentSalvo.add(cannonBall);
        if (tempCannonsAmount == 0) {
            salvoHandler.fireSalvoCannonade(currentSalvo);
            currentSalvo.clear();
            tempCannonsAmount = 4;
            boardViewUpdaterListener.update("...!!!!FIRE!!!!...\n");
        } else {
            tempCannonsAmount--;
        }
    }

//    private void updatePlayerBoard(Set<Integer> positions) {
//        positions.forEach(data -> {
//            Label label = new Label();
//            label.setText("X");
//            int[] twoDimensionalBoardPosition = PositionUtils.calculateFromOneDimensionalPosition(data - 1, BOARD_SIZE);
//            playerGridPane.add(label, twoDimensionalBoardPosition[X], twoDimensionalBoardPosition[Y]);
//        });
//    }

    //TODO Solve magic number bug(?)
    private void updateEnemyBoard(Set<Integer> positions) {
        positions.forEach(data -> {
            Button enemyMast = enemyMastPositions.get(data - 1);
            enemyMast.setText("X");
            enemyMast.setDisable(true);
        });
    }
}
