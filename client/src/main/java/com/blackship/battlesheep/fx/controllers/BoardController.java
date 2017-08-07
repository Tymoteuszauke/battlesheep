package com.blackship.battlesheep.fx.controllers;

import com.blackship.battlesheep.communication.ClientCommunicationHandlerKeeper;
import com.blackship.battlesheep.communication.RandomizedBoardReceiver;
import com.blackship.battlesheep.communication.network.AppClientCommunicationHandler;
import com.blackship.battlesheep.fx.SalvoHandler;
import com.blackship.battlesheep.fx.board_view_updater.BoardViewUpdater;
import com.blackship.battlesheep.fx.board_view_updater.BoardViewUpdaterListener;
import com.blackship.battlesheep.fx.utils.ButtonUtils;
import com.blackship.battlesheep.fx.utils.PositionUtils;
import com.blackship.battlesheep.utils.LogUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Mateusz Słaboński
 * @since 26.07.2017
 */
public class BoardController implements BoardViewUpdaterListener {

    private final static Logger log = LogUtils.getLogger();
    private final static int BOARD_SIZE = 10;
    private final static int X = 0;
    private final static int Y = 1;
    private final static int BOARD_OFFSET = 1;

    private BoardViewUpdater boardViewUpdater;
    private List<Button> enemyMastPositions;
    private List<Button> playerMastPositions;

    @FXML
    public TextArea loggerInfoTextArea;

    @FXML
    public Button tutorialButton;

    @FXML
    public Button moveButton;

    @FXML
    public GridPane playerGridPane;

    @FXML
    public GridPane enemyGridPane;

    @FXML
    public void handleCreateEnemyBoardActionButton(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        enemyMastPositions = ButtonUtils.generateButtons(BOARD_SIZE);
        enemyMastPositions.forEach(this::setButtonListener);

        log.info("...Drawing Board...");
        loggerInfoTextArea.appendText("...Drawing Board... \n");

        arrangeButtonsOnPlayerBoard();
        arrangeButtonsOnEnemyBoard();
        moveButton.setVisible(false);
        boardViewUpdater = new BoardViewUpdater
                .BoardViewUpdaterBuilder()
                .boardViewUpdaterListener(this)
                .playerMastPositions(playerMastPositions)
                .enemyMastPositions(enemyMastPositions)
                .salvoHandler(setupSalvoHandler())
                .build();
    }

    private void arrangeButtonsOnEnemyBoard() {
        IntStream.range(0, BOARD_SIZE * BOARD_SIZE).forEach(data -> {
            int[] twoDimensionalBoardPosition = PositionUtils.calculateFromOneDimensionalPosition(data, BOARD_SIZE);
            enemyGridPane.add(enemyMastPositions.get(data), twoDimensionalBoardPosition[X], twoDimensionalBoardPosition[Y]);
        });
    }

    private void arrangeButtonsOnPlayerBoard() throws IOException, ClassNotFoundException {
        playerMastPositions = new ArrayList<>();
        RandomizedBoardReceiver randomizedBoardReceiver = new RandomizedBoardReceiver(ClientCommunicationHandlerKeeper.Instance.getAppClientCommunicationHandler());
        List<Integer> randomizedPlayerShips = randomizedBoardReceiver.receiveRandomizedPlayerBoard();
        randomizedPlayerShips.forEach(data -> {
            Button button = ButtonUtils.createStyledButton(data);
            button.setDisable(true);
            button.setStyle(ButtonUtils.disabledButtonColorStyle());
            playerMastPositions.add(button);
            int[] twoDimensionalBoardPosition = PositionUtils.calculateFromOneDimensionalPosition(data - BOARD_OFFSET, BOARD_SIZE);
            playerGridPane.add(button, twoDimensionalBoardPosition[X], twoDimensionalBoardPosition[Y]);
        });
    }

    private SalvoHandler setupSalvoHandler() throws IOException {
        return new SalvoHandler(ClientCommunicationHandlerKeeper.Instance.getAppClientCommunicationHandler());
    }

    private Button setButtonListener(Button button) {
        button.setOnAction(event -> {
            button.setStyle(ButtonUtils.pinkButtonColorStyle());
            log.info(String.format("...Preparing to shoot into position %s...", button.getText()));
            loggerInfoTextArea.appendText(String.format("...Player shoots on position %s...\n", button.getId()));
            button.setDisable(true);
            enemyGridPane.requestFocus();
            boardViewUpdater.feedCannon(Integer.parseInt(button.getText()));
        });
        return button;
    }

    @FXML
    private void tutorialButtonActionHandler() throws IOException {
        System.out.println(getClass().getResource("/tutorial.txt").toString());
        String path = getClass().getResource("/tutorial.txt").toString().substring(5);
        String tutorialTextFileContent = new String(Files.readAllBytes(Paths.get(path)));

        loggerInfoTextArea.appendText(tutorialTextFileContent + System.lineSeparator());
    }

    @Override
    public void update(String log) {
        loggerInfoTextArea.appendText(log);
    }
}
