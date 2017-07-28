package com.blackship.battlesheep.communication.network.packet;

import com.blackship.battlesheep.communication.packet.Packet;
import com.blackship.battlesheep.communication.packet.PacketConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class NetworkPacketConverter implements PacketConverter {

    @Override
    public byte[] toByte(Packet packet) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ObjectOutputStream(byteArrayOutputStream).writeObject(packet);

        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public Packet toPacket(byte[] bytes) throws IOException, ClassNotFoundException {
        return (Packet) new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
    }

}
