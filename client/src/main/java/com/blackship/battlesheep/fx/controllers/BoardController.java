package com.blackship.battlesheep.fx.controllers;

import com.blackship.battlesheep.communication.network.AppClientCommunicationHandler;
import com.blackship.battlesheep.communication.network.AppClientSocket;
import com.blackship.battlesheep.fx.BoardViewUpdater;
import com.blackship.battlesheep.fx.BoardViewUpdaterListener;
import com.blackship.battlesheep.fx.utils.ButtonUtils;
import com.blackship.battlesheep.fx.utils.PositionUtils;
import com.blackship.battlesheep.utils.LogUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.*;

/**
 * @author Mateusz Słaboński
 * @since 26.07.2017
 */
public class BoardController implements BoardViewUpdaterListener {

    private final static Logger log = LogUtils.getLogger();
    private final static int BOARD_SIZE = 10;
    private final static int X = 0;
    private final static int Y = 1;

    private BoardViewUpdater boardViewUpdater;
    private List<Button> enemyMastPositions;

    @FXML
    public TextArea loggerInfoTextArea;

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

        arrangeButtonsOnEnemyBoard();
        moveButton.setVisible(false);
        boardViewUpdater = new BoardViewUpdater
                .BoardViewUpdaterBuilder()
                .boardViewUpdaterListener(this)
                .enemyMastPositions(enemyMastPositions)
                .salvoHandler(setupSalvoHandler())
                .build();
    }

    private void arrangeButtonsOnEnemyBoard() {
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            int[] twoDimensionalBoardPosition = PositionUtils.calculateFromOneDimensionalPosition(i, BOARD_SIZE);
            enemyGridPane.add(enemyMastPositions.get(i), twoDimensionalBoardPosition[X], twoDimensionalBoardPosition[Y]);
        }
    }

    private SalvoHandler setupSalvoHandler() throws IOException {
        AppClientSocket appClientSocket = new AppClientSocket("localhost", 8096);
        AppClientCommunicationHandler appClientCommunicationHandler = new AppClientCommunicationHandler(appClientSocket);
        appClientCommunicationHandler.connect();
        return new SalvoHandler(appClientCommunicationHandler);
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

    @Override
    public void update(String log) {
        loggerInfoTextArea.appendText(log);
    }
}
