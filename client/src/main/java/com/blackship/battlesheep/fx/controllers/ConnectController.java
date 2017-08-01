package com.blackship.battlesheep.fx.controllers;

import com.blackship.battlesheep.utils.LogUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * @author Mateusz Słaboński
 * @since 26.07.2017
 */
public class ConnectController extends Application {

    private final static Logger log = LogUtils.getLogger();

    @FXML
    public Button connectButton;

    @FXML
    public TextField ipAddressTextField;

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("...Starting battlesheep client...");
        primaryStage.setTitle("Blackship battlesheep");
        Parent root = FXMLLoader.load(getClass().getResource("/fx/ConnectView.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void handleConnectButtonAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fx/BoardView.fxml"));

        Scene scene = new Scene(parent);
        Stage boardStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        boardStage.setScene(scene);

        log.info("...Connecting to ...");
        boardStage.show();
    }
}
