package com.blackship.battlesheep.communication;

import com.blackship.battlesheep.communication.network.AppClientCommunicationHandler;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketBoard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RandomizedBoardReceiver {

    private final AppClientCommunicationHandler appClientCommunicationHandler;

    public RandomizedBoardReceiver(AppClientCommunicationHandler appClientCommunicationHandler) {
        this.appClientCommunicationHandler = appClientCommunicationHandler;
    }

    public List<Integer> receiveRandomizedPlayerBoard() throws IOException, ClassNotFoundException {

        Packet packet = appClientCommunicationHandler.read();
        return ((PacketBoard)packet).getPlayerPositions()
                .stream()
                .flatMap(i -> i.stream())
                .collect(Collectors.toList());
    }
}
