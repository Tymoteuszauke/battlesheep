package com.blackship.battlesheep.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.Node;

import java.io.IOException;

public class ConnectController extends Application {

    private final static Logger logger = LoggerFactory.getLogger(ConnectController.class);

    @FXML
    public Button connectButton;

    @FXML
    public TextField ipAddressTextField;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Blackship battlesheep");
        Parent root = FXMLLoader.load(getClass().getResource("/fx/ConnectView.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void handleConnectButtonAction(ActionEvent actionEvent) throws IOException {
        Parent parent = null;

        parent = FXMLLoader.load(getClass().getResource("/fx/BoardView.fxml"));

        Scene scene = new Scene(parent);
        Stage boardStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        boardStage.setScene(scene);
        boardStage.show();
    }

}
