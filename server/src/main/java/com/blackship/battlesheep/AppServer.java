package com.blackship.battlesheep;

import com.blackship.battlesheep.communication.network.AppServerSocket;
import com.blackship.battlesheep.communication.network.ServerCommunicationHandler;
import com.blackship.battlesheep.game.state.exceptions.FirstPlayerWon;
import com.blackship.battlesheep.game.state.exceptions.SecondPlayerWon;

import java.io.IOException;

/**
 * @author milosz
 * @since 31.07.2017
 */
public class AppServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException, FirstPlayerWon, SecondPlayerWon {

        new ServerCommunicationHandler(
                new AppServerSocket(8096))
                .acceptClients()
                //.sendHardcodedBoardsToClients()
                .echo();
    }

}
