package com.blackship.battlesheep.fx;

import com.blackship.battlesheep.AppClient;
import com.blackship.battlesheep.communication.network.AppClientCommunicationHandler;
import com.blackship.battlesheep.communication.network.AppClientSocket;

/**
 * Created by Mateusz on 04.08.2017.
 */
public enum ClientCommunicationHandlerKeeper {
    Instance;

    private AppClientCommunicationHandler appClientCommunicationHandler;

    public void setAppClientCommunicationHandler(AppClientCommunicationHandler appClientCommunicationHandler) {
        this.appClientCommunicationHandler = appClientCommunicationHandler;
    }

    public AppClientCommunicationHandler getAppClientCommunicationHandler() {
        return appClientCommunicationHandler;
    }
}
