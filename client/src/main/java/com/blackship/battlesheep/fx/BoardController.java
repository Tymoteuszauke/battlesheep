package com.blackship.battlesheep.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class BoardController {

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
        loggerInfoTextArea.appendText(moveValue + "\n");
        positionTextField.clear();

        int position = Integer.parseInt(moveValue) - 1;
        int[] positionCoordinates = calculateOneDimensionalPosition(position);

        Label label = new Label("X");
        label.setStyle("-fx-font-size: 25px;");

        playerGridPane.add(label, positionCoordinates[0], positionCoordinates[1]);
        GridPane.setHalignment(label, HPos.CENTER);

    }

    private int[] calculateOneDimensionalPosition(int position) {
        int x = position % 10;
        int y = position / 10;
        return new int[] {x, y};

    }

}
