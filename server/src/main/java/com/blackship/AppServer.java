package com.blackship;

import com.blackship.blacksheep.communication.network.AppServerSocket;
import com.blackship.blacksheep.communication.network.ServerCommunicationHandler;

import java.io.IOException;

/**
 * @author milosz
 * @since 31.07.2017
 */
public class AppServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        new ServerCommunicationHandler(
                new AppServerSocket(8096))
                .acceptClients()
                .sendHardcodedBoardsToClients()
                .echo();
    }

}
