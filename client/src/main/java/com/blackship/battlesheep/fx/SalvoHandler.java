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

    private List<Integer> enemyDestroyedMasts;
    private List<Integer> playerDestroyedMasts;


    private AppClientCommunicationHandler appClientCommunicationHandler;

    public SalvoHandler(AppClientCommunicationHandler appClientCommunicationHandler) {
        this.appClientCommunicationHandler = appClientCommunicationHandler;
        enemyDestroyedMasts = new ArrayList<>();
        playerDestroyedMasts = new ArrayList<>();
    }

    public boolean encounterSalvos() throws IOException, ClassNotFoundException {

        enemyDestroyedMasts.clear();
        playerDestroyedMasts.clear();
        PacketMove packet = (PacketMove) appClientCommunicationHandler.read();

        if (((Packet)packet).getPacketType() == PacketType.MOVE) {
            enemyDestroyedMasts.addAll(packet.getPositions().get(1));
            playerDestroyedMasts.addAll(packet.getPositions().get(0));

            setChanged();
            notifyObservers();

            return true;
        }
        return false;
    }

    public List<Integer> getEnemyDestroyedMasts() {
        return enemyDestroyedMasts;
    }

    public List<Integer> getPlayerDestroyedMasts() {
        return playerDestroyedMasts;
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
