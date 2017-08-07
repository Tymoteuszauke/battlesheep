package com.blackship.battlesheep.fx.board_view_updater;

import com.blackship.battlesheep.fx.SalvoHandler;
import com.blackship.battlesheep.fx.utils.ButtonUtils;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.MalformedURLException;
import java.util.*;

/**
 * @author Mateusz Słaboński
 * @since 01.08.2017
 */
public class BoardViewUpdater implements Observer {

    private BoardViewUpdaterListener boardViewUpdaterListener;
    private List<Button> enemyMastPositions = new ArrayList<>();
    private List<Button> playerMastPositions = new ArrayList<>();
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
        if (salvoHandler.hasWinner()) {
            boardViewUpdaterListener.update("The winner is: " + salvoHandler.getWinner());
        } else {
            List<Integer> enemyHitPositions = salvoHandler.getEnemyDestroyedMasts();
            updateEnemyBoard(enemyHitPositions);
            updatePlayerBoard(salvoHandler.getPlayerDestroyedMasts());
            enemyMastPositions
                    .stream()
                    .filter(data -> !data.getText().equals("X"))
                    .forEach(data -> {
                        data.setDisable(false);
                        data.setStyle(ButtonUtils.defaultButtonColorStyle());
                    });
        }
    }

    public void feedCannon(int cannonBall) {
        loadedCannons.add(cannonBall);
        long availableCannons = playerMastPositions
                .stream()
                .filter(data -> !data.getText().equals("X"))
                .count();

        if (loadedCannons.size() == availableCannons) {
            playCannonSound();
            fireCannons(loadedCannons);
        }
    }

    private void fireCannons(List<Integer> cannons) {
        salvoHandler.fireSalvoCannonade(cannons);
        loadedCannons.clear();
        boardViewUpdaterListener.update("...!!!!FIRE!!!!...\n");
    }

    private void updatePlayerBoard(List<Integer> positions) {
        playerMastPositions.forEach(button -> {
            if (positions.contains(Integer.parseInt(button.getId()))) {
                button.setText("X");
                button.setDisable(true);
            }
        });
    }

    private void updateEnemyBoard(List<Integer> positions) {
        enemyMastPositions.forEach(button -> {
            if (positions.contains(Integer.parseInt(button.getId()))) {
                button.setText("X");
                button.setDisable(true);
            }
        });
    }

    private void playCannonSound() {
        new Thread(() -> {
            System.out.println(getClass().getResource("/sounds/cannons.mp3").toString());
            String pathToSound = getClass().getResource("/sounds/cannons.mp3").toString().substring(5);
            Media sound = null;
            try {
                sound = new Media(new File(pathToSound).toURL().toExternalForm());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }).start();
    }
}
