package com.blackship.battlesheep.fx.controllers;

import com.blackship.battlesheep.communication.network.AppClientCommunicationHandler;
import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketBoards;
import com.blackship.battlesheep.communication.packet.PacketMove;
import com.blackship.battlesheep.communication.packet.enums.PacketType;
import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.*;

/**
 * @author Mateusz Słaboński
 * @since 31.07.2017
 */
public class SalvoHandler extends Observable {

    private static final Logger log = LogUtils.getLogger();

    private Set<Integer> enemyHitPositions = new HashSet<>();
    private Set<Integer> playerHitPositions = new HashSet<>();

    private AppClientCommunicationHandler appClientCommunicationHandler;

    public SalvoHandler(AppClientCommunicationHandler appClientCommunicationHandler) {
        this.appClientCommunicationHandler = appClientCommunicationHandler;
        enemyHitPositions = new HashSet<>();
        enemyHitPositions = new HashSet<>();
    }

    public boolean encounterSalvos() throws IOException, ClassNotFoundException {

        PacketMove packet = (PacketMove) appClientCommunicationHandler.read();

        if (((Packet)packet).getPacketType() == PacketType.MOVE) {
            enemyHitPositions.addAll(packet.getPositions());
//            playerHitPositions.addAll(packet.getPlayerPositions());
            setChanged();
            notifyObservers();
            enemyHitPositions.clear();
            playerHitPositions.clear();
            return true;
        }
        return false;
    }

    public Set<Integer> getEnemyHitPositions() {
        return enemyHitPositions;
    }

    public Set<Integer> getPlayerHitPositions() {
        return playerHitPositions;
    }

    public void fireSalvoCannonade(List<Integer> positionsToBeBalled) {

        try {
            Packet packet = PacketFactory.createMove();
            ((PacketMove) packet).addPositions(positionsToBeBalled);
            appClientCommunicationHandler.write(packet);
            log.info(String.format("...!!!!FIRE!!!!... \n%s", packet.getPacketType().name()));

            encounterSalvos();

        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
