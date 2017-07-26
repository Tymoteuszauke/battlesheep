package com.blackship.battlesheep.fx.controllers;

import com.blackship.battlesheep.fx.controllers.utils.PositionUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class BoardController {

    private final static int BOARD_SIZE = 10;

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

        String moveValue = "";

        moveValue = positionTextField.getText();
        loggerInfoTextArea.appendText(moveValue + "\n");
        positionTextField.clear();

        int position = Integer.parseInt(moveValue) - 1;
        int[] positionCoordinates = PositionUtils.calculateOneDimensionalPosition(position, BOARD_SIZE);

        Label label = new Label("X");
        label.setStyle("-fx-font-size: 25px;");
        positionTextField.requestFocus();
        playerGridPane.add(label, positionCoordinates[0], positionCoordinates[1]);
        GridPane.setHalignment(label, HPos.CENTER);


    }



//    public Label createField(int position) {
//        int[] positionCoordinates = PositionUtils.calculateOneDimensionalPosition(position, BOARD_SIZE);
//
//
//    }
}
