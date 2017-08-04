package com.blackship.battlesheep.bus;

/**
 * @author milosz
 * @since 04.08.2017
 */
public class Event {

    private final Object object;

    public Event(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

}
