package com.blackship.battlesheep.fx.controllers;

import com.blackship.battlesheep.fx.controllers.utils.PositionUtils;
import com.blackship.battlesheep.utils.LogUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;

/**
 * @author Mateusz Słaboński
 * @since 26.07.2017
 */
public class BoardController {

    private final static Logger log = LogUtils.getLogger();
    private final static int BOARD_SIZE = 10;
    private final static String LINE_SEPARATOR = System.getProperty("line.separator");

    @FXML
    public TextArea loggerInfoTextArea;

    @FXML
    public Button moveButton;

    @FXML
    public TextField positionTextField;

    @FXML
    public GridPane playerGridPane;

    @FXML
    public GridPane enemyGridPane;

    @FXML
    public void handleMoveButtonAction(ActionEvent actionEvent) {

        String moveValue = positionTextField.getText();
        loggerInfoTextArea.appendText(moveValue + LINE_SEPARATOR);
        positionTextField.clear();

        int position = Integer.parseInt(moveValue);
        int[] positionCoordinates = PositionUtils.calculateFromOneDimensionalPosition(position - 1, BOARD_SIZE);

        Label label = new Label("X");
        label.setStyle("-fx-font-size: 25px;");
        positionTextField.requestFocus();
        playerGridPane.add(label, positionCoordinates[0], positionCoordinates[1]);
        GridPane.setHalignment(label, HPos.CENTER);
        log.info(String.format("...Player shoots on position %d", position));
    }
}
