package com.blackship.battlesheep.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    }

}
