package com.blackship.battlesheep.bus;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author milosz
 * @since 04.08.2017
 */
public class EventBus implements Runnable {

    private Queue<Event> events = new LinkedList<>();
    private List<Listener> listeners = new LinkedList<>();
    private boolean isFinished;

    public void register(Listener listener) {
        listeners.add(listener);
    }

    public void submit(Event event) {
        events.offer(event);
    }

    private void pushEvents() {
        if (!events.isEmpty()) {
            Event event = events.poll();
            listeners.stream().forEach(i -> i.update(event));
        }
    }

    @Override
    public void run() {
        while(!isFinished) {
            pushEvents();
        }
    }
}
