package com.blackship.battlesheep.fx;

import com.blackship.battlesheep.communication.network.AppClientCommunicationHandler;
import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
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

    private List<Integer> enemyHitPositions;
    private List<Integer> playerHitPositions;

    private AppClientCommunicationHandler appClientCommunicationHandler;

    public SalvoHandler(AppClientCommunicationHandler appClientCommunicationHandler) {
        this.appClientCommunicationHandler = appClientCommunicationHandler;

        enemyHitPositions = new ArrayList<>();
        playerHitPositions = new ArrayList<>();
    }

    public boolean encounterSalvos() throws IOException, ClassNotFoundException {

        enemyHitPositions.clear();
        playerHitPositions.clear();
        PacketMove packet = (PacketMove) appClientCommunicationHandler.read();

        if (((Packet)packet).getPacketType() == PacketType.MOVE) {
            enemyHitPositions.addAll(packet.getPositions().get(0));
            playerHitPositions.addAll(packet.getPositions().get(1));

            setChanged();
            notifyObservers();

            return true;
        }
        return false;
    }

    public List<Integer> getEnemyHitPositions() {
        return enemyHitPositions;
    }

    public List<Integer> getPlayerHitPositions() {
        return playerHitPositions;
    }

    public boolean fireSalvoCannonade(List<Integer> positionsToBeBalled) {

        try {
            Packet packet = PacketFactory.createMove();
            ((PacketMove) packet).addPositions(positionsToBeBalled);
            appClientCommunicationHandler.write(packet);
            log.info(String.format("...!!!!FIRE!!!!... \n%s", packet.getPacketType().name()));

            encounterSalvos();

        } catch (IOException | ClassNotFoundException e) {
            log.error(e.getMessage());
        }
        return true;
    }
}
