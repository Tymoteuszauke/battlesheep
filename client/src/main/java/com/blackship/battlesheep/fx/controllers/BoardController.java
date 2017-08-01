package com.blackship.battlesheep.fx.controllers;

import com.blackship.battlesheep.fx.controllers.utils.ButtonUtils;
import com.blackship.battlesheep.fx.controllers.utils.PositionUtils;
import com.blackship.battlesheep.utils.LogUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.IntStream;

/**
 * @author Mateusz Słaboński
 * @since 26.07.2017
 */
public class BoardController implements Observer {

    private final static Logger log = LogUtils.getLogger();
    private final static int BOARD_SIZE = 10;
    private final static int X = 0;
    private final static int Y = 1;

    private List<Button> buttons;

    private NetworkObservableHandler networkObservableHandler;

    @FXML
    public TextArea loggerInfoTextArea;

    @FXML
    public Button moveButton;

    @FXML
    public GridPane playerGridPane;

    @FXML
    public GridPane enemyGridPane;

    @FXML
    public void handleCreateEnemyBoardActionButton(ActionEvent actionEvent) {
        buttons = generateButtons();

        networkObservableHandler = new NetworkObservableHandler();
        networkObservableHandler.addObserver(this);

        log.info("...Creating Board...");
        loggerInfoTextArea.appendText("...Creating Board... \n");

        for (int i = 0; i < BOARD_SIZE*BOARD_SIZE; i++) {
            int[] twoDimensionalBoardPosition = PositionUtils.calculateFromOneDimensionalPosition(i, BOARD_SIZE);
            enemyGridPane.add(buttons.get(i), twoDimensionalBoardPosition[X], twoDimensionalBoardPosition[Y]);
        }
        moveButton.setVisible(false);
    }

    private Button generateButton(int value) {
        Button button = ButtonUtils.createStyledButton(value);
        //TODO do something with layout
        button.setOnAction(event -> {
            button.setStyle(ButtonUtils.pinkButtonColorStyle());
            log.info(String.format("...Preparing to shoot into position %s...", button.getId()));
            loggerInfoTextArea.appendText(String.format("...Player shoots on position %s...\n", button.getId()));
            button.setDisable(true);
            enemyGridPane.requestFocus();
            networkObservableHandler.positionsChanged(value);
        });
        return button;
    }

    private List<Button> generateButtons() {
        List<Button> buttons = new ArrayList<>();
        IntStream
                .rangeClosed(1, BOARD_SIZE * BOARD_SIZE)
                .forEach(data -> buttons.add(generateButton(data)));
        return buttons;
    }


    @Override
    public void update(Observable o, Object arg) {
        networkObservableHandler = (NetworkObservableHandler) o;

        Label label = new Label();
        label.setText("X");

        networkObservableHandler
                .getPositions()
                .forEach(data -> {
                    int[] positions = PositionUtils.calculateFromOneDimensionalPosition(data - 1, BOARD_SIZE);
                    playerGridPane.add(label, positions[X], positions[Y]);
                });

        buttons.forEach(data -> data.setStyle(ButtonUtils.defaultButtonColorStyle()));
    }
}
