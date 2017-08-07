package com.blackship.battlesheep.fx;

import com.blackship.battlesheep.communication.network.AppClientCommunicationHandler;
import com.blackship.battlesheep.communication.network.packet.NetworkPacketWinner;
import com.blackship.battlesheep.communication.network.packet.PacketFactory;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketMove;
import com.blackship.battlesheep.communication.packet.PacketWinner;
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
    private AppClientCommunicationHandler appClientCommunicationHandler;
    private List<Integer> enemyDestroyedMasts;
    private List<Integer> playerDestroyedMasts;

    private String winner;
    private boolean hasWinner;

    public SalvoHandler(AppClientCommunicationHandler appClientCommunicationHandler) {
        this.appClientCommunicationHandler = appClientCommunicationHandler;
        enemyDestroyedMasts = new ArrayList<>();
        playerDestroyedMasts = new ArrayList<>();
    }

    public void encounterSalvos() throws IOException, ClassNotFoundException {

        enemyDestroyedMasts.clear();
        playerDestroyedMasts.clear();
        Packet packet = appClientCommunicationHandler.read();

        if (packet.getPacketType() == PacketType.MOVE) handlePacketMove((PacketMove) packet);
        if (packet.getPacketType() == PacketType.WINNER) handlePacketWinner((PacketWinner) packet);

        setChanged();
        notifyObservers();
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

    public boolean hasWinner() {
        return hasWinner;
    }

    public String getWinner() {
        return winner;
    }

    private void handlePacketMove(PacketMove packetMove) {
        enemyDestroyedMasts.addAll(packetMove.getPositions().get(1));
        playerDestroyedMasts.addAll(packetMove.getPositions().get(0));
    }

    private void handlePacketWinner(PacketWinner packetWinner) {
        winner = packetWinner.getWinner();
        hasWinner = true;
    }
}
