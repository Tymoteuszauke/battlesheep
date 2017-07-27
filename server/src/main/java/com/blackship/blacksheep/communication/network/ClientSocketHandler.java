package com.blackship.blacksheep.communication.network;

import com.blackship.battlesheep.common.communication.Reader;
import com.blackship.battlesheep.common.communication.Streams;
import com.blackship.battlesheep.common.communication.Writer;
import com.blackship.battlesheep.common.communication.network.NetworkReader;
import com.blackship.battlesheep.common.communication.network.NetworkStreams;
import com.blackship.battlesheep.common.communication.network.NetworkWriter;
import com.blackship.battlesheep.common.communication.network.packet.NetworkPacketConverter;
import com.blackship.battlesheep.common.communication.packet.Packet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author milosz
 * @since 7/26/17
 */
class ClientSocketHandler {

    private Socket client;
    private Writer clientWriter;
    private Reader clientReader;

    ClientSocketHandler(Socket client) throws IOException {
        this.client = client;
        setReaderAndWriter();
    }

    ClientSocketHandler setReaderAndWriter() throws IOException {
        Streams clientStreams = new NetworkStreams(client);

        InputStream clientInputStream = clientStreams.getInput()
                .orElseThrow(() -> new IOException("null inputstream !"));
        OutputStream clientOutputStream = clientStreams.getOutput()
                .orElseThrow(() -> new IOException("Null outpustream !"));

        clientReader = new NetworkReader(clientInputStream);
        clientWriter = new NetworkWriter(clientOutputStream);

        return this;
    }

    void write(Packet packet) throws IOException {
        clientWriter.write(new NetworkPacketConverter().toByte(packet));
    }

    Packet read() throws IOException, ClassNotFoundException {
        return new NetworkPacketConverter().toPacket(clientReader.read());
    }
}
