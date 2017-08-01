package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.Reader;
import com.blackship.battlesheep.communication.Streams;
import com.blackship.battlesheep.communication.Writer;
import com.blackship.battlesheep.communication.network.packet.NetworkPacketConverter;
import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author milosz
 * @since 27.7.17
 */
class ClientSocketHandler {

    private Socket client;
    private Writer clientWriter;
    private Reader clientReader;
    private PacketConverter clientPacketConverter;

    ClientSocketHandler(Socket client) throws IOException {
        this.client = client;
        clientPacketConverter = new NetworkPacketConverter();
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
        clientWriter.write(clientPacketConverter.toByte(packet));
    }

    Packet read() throws IOException, ClassNotFoundException {
        return clientPacketConverter.toPacket(clientReader.read());
    }

    @Override
    public String toString() {
        return client.toString();
    }
}
