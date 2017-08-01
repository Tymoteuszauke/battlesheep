package com.blackship.battlesheep.fx.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Mateusz Słaboński
 * @since 31.07.2017
 */
public class NetworkObservableHandler extends Observable {

    private List<Integer> positions = new ArrayList<>();


    public NetworkObservableHandler() {
        positions = new ArrayList<>();
    }

    public void positionsChanged(int position) {
        positions.add(position);
        setChanged();
        notifyObservers();
        positions.clear();
    }

    public List<Integer> getPositions() {
        return positions;
    }
}
