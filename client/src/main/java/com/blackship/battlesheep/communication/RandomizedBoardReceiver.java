package com.blackship.battlesheep.communication;

import com.blackship.battlesheep.communication.network.AppClientCommunicationHandler;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketBoard;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mateusz Słaboński
 * @since 26.07.2017
 */
public class RandomizedBoardReceiver {

    private final AppClientCommunicationHandler appClientCommunicationHandler;

    public RandomizedBoardReceiver(AppClientCommunicationHandler appClientCommunicationHandler) {
        this.appClientCommunicationHandler = appClientCommunicationHandler;
    }

    public List<Integer> receiveRandomizedPlayerBoard() throws IOException, ClassNotFoundException {

        Packet packet = appClientCommunicationHandler.read();
        return ((PacketBoard)packet).getPlayerPositions()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
