package com.blackship.battlesheep.common.communication.network.packet;

import com.blackship.battlesheep.common.communication.packet.Packet;
import com.blackship.battlesheep.common.communication.packet.PacketConverter;

import java.io.*;

/**
 * @author milosz
 * @since 7/26/17
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
