package com.blackship.battlesheep.bus;

import com.blackship.battlesheep.bus.exceptions.InterruptedGameThreadException;
import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author milosz
 * @since 04.08.2017
 */
public class EventBus implements Runnable {

    private static final Logger log = LogUtils.getLogger();

    private Queue<Event> events = new LinkedList<>();
    private List<Listener> listeners = new LinkedList<>();
    private transient boolean isFinished;

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

    public void stop() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.warn(e.getMessage());
            Thread.currentThread().interrupt();
            throw new InterruptedGameThreadException("...Game thread was interrupted!...", e);
        }
        isFinished = true;
    }

    @Override
    public void run() {
        while(!isFinished) {
            pushEvents();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.warn(e.getMessage());
                Thread.currentThread().interrupt();
                throw new InterruptedGameThreadException("...Game thread was interrupted!...", e);
            }
        }
    }
}
