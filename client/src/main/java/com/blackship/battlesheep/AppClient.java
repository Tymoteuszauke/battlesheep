package com.blackship.battlesheep;

import com.blackship.battlesheep.fx.controllers.ConnectController;
import javafx.application.Application;

/**
 * @author Mateusz Słaboński
 * @since 27.07.2017
 */
public class AppClient  {
    public static void main(String[] args) {
        new Thread(() -> Application.launch(ConnectController.class)).start();
    }
}
