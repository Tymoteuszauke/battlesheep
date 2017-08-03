package com.blackship.battlesheep;

import com.blackship.battlesheep.fx.controllers.ConnectController;
import javafx.application.Application;

import java.io.IOException;

/**
 * @author Mateusz Słaboński
 * @since 27.07.2017
 */
public class AppClient  {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Thread(() -> Application.launch(ConnectController.class)).start();
    }
}
