package com.blackship.battlesheep.communication;

import java.io.IOException;

/**
 * @author milosz
 * @since 28.07.2017
 *
 * Write bytes to stream.
 */
public interface Writer {
    /**
     * Write bytes to stream.
     * @param bytes Given bytes.
     * @throws IOException Exception if there is a problem with stream.
     */
    void write(byte[] bytes) throws IOException;
}
