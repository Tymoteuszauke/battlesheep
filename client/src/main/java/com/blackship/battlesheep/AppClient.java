package com.blackship.battlesheep;

import com.blackship.battlesheep.communication.network.AppClientCommunicationHandler;
import com.blackship.battlesheep.communication.network.AppClientHandler;
import com.blackship.battlesheep.communication.network.AppClientSocket;
import com.blackship.battlesheep.fx.controllers.ConnectController;
import javafx.application.Application;

import java.io.IOException;

/**
 * @author Mateusz Słaboński
 * @since 27.07.2017
 */
public class AppClient  {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        new AppClientHandler(
                new AppClientCommunicationHandler(
                        new AppClientSocket("localhost", 8096)))
                .connectWithServer()
                .receiveBoards()
                .startClientLoop();

        new Thread(() -> Application.launch(ConnectController.class)).start();
    }
}
