package com.blackship.battlesheep.bus.exceptions;

public class InterruptedGameThreadException extends RuntimeException {
    public InterruptedGameThreadException(String message, Throwable e) {
        super(message, e);
    }
}
