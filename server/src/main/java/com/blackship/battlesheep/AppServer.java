package com.blackship.battlesheep;

import com.blackship.battlesheep.communication.network.AppServerSocket;
import com.blackship.battlesheep.communication.network.ServerCommunicationHandler;
import com.blackship.battlesheep.game.state.exceptions.WrongStateException;

import java.io.IOException;

/**
 * @author milosz
 * @since 31.07.2017
 */
public class AppServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException, WrongStateException, InterruptedException {
        new ServerCommunicationHandler(
                new AppServerSocket(8096))
                .acceptClients()
                .echo();
    }
}
