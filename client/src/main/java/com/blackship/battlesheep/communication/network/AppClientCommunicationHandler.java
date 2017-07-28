package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.Reader;
import com.blackship.battlesheep.communication.Streams;
import com.blackship.battlesheep.communication.Writer;
import com.blackship.battlesheep.communication.network.packet.NetworkPacketConverter;
import com.blackship.battlesheep.communication.packet.Packet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author milosz
 * @since 26.7.17
 */
class AppClientCommunicationHandler {

    private AppClientSocket client;
    private Writer clientWriter;
    private Reader clientReader;

    AppClientCommunicationHandler(AppClientSocket client) {
        this.client = client;
    }

    void connect() throws IOException {
        client.connect();

        Streams clientStreams = new NetworkStreams(client.getSocket());

        InputStream clientInputStream = clientStreams.getInput()
                .orElseThrow(() -> new IOException("Null inputstream !"));
        OutputStream clientOutputStream = clientStreams.getOutput()
                .orElseThrow(() -> new IOException("Null outpustream !"));

        clientReader = new NetworkReader(clientInputStream);
        clientWriter = new NetworkWriter(clientOutputStream);
    }

    void write(Packet packet) throws IOException {
        clientWriter.write(new NetworkPacketConverter().toByte(packet));
    }

    Packet read() throws IOException, ClassNotFoundException {
        return new NetworkPacketConverter().toPacket(clientReader.read());
    }

}