package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.bus.Event;
import com.blackship.battlesheep.bus.Listener;
import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketWinner;
import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * @author milosz
 * @since 04.08.2017
 */
public class ReportClients implements Listener {

    private final static Logger log = LogUtils.getLogger();

    private ClientSocketHandler firstClient;
    private ClientSocketHandler secondClient;
    private String winner;

    public ReportClients(ClientSocketHandler firstClient, ClientSocketHandler secondClient, String winner) {
        this.firstClient = firstClient;
        this.secondClient = secondClient;
        this.winner = winner;
    }

    public void report() {
        //TODO send package to others
        log.info("...MESSAGE FROM BUS: PLAYER WON -> " + winner + " ...");
        log.info("...Sending packet about won to clients...");
        try {
            firstClient.write(PacketFactory.createWinner().setWinner(winner));
            secondClient.write(PacketFactory.createWinner().setWinner(winner));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        report();
    }
}
