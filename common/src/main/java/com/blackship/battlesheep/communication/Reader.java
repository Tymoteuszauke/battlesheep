package com.blackship.battlesheep.communication;

import java.io.IOException;

/**
 * @author milosz
 * @since 28.07.2017
 *
 * Read bytes from stream.
 */
public interface Reader {
    /**
     * Read bytes from stream.
     * @return Bytes from stream.
     * @throws IOException Exception if there is a problem with stream.
     */
    byte[] read() throws IOException;
}
