package com.blackship.battlesheep.communication;

import com.blackship.battlesheep.communication.network.AppClientCommunicationHandler;

/**
 * @author Mateusz Słaboński
 * @since 27.07.2017
 */
public enum ClientCommunicationHandlerKeeper {
    INSTANCE;

    private AppClientCommunicationHandler appClientCommunicationHandler;

    public void setAppClientCommunicationHandler(AppClientCommunicationHandler appClientCommunicationHandler) {
        this.appClientCommunicationHandler = appClientCommunicationHandler;
    }

    public AppClientCommunicationHandler getAppClientCommunicationHandler() {
        return appClientCommunicationHandler;
    }
}
