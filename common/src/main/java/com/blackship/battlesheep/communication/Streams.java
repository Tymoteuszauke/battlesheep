package com.blackship.battlesheep.communication;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

/**
 * @author milosz
 * @since 28.07.2017
 *
 * Streams gives methods to return I/O streams.
 */
public interface Streams {
    /**
     * Method returns output stream.
     * @return OutputStream.
     */
    Optional<OutputStream> getOutput();

    /**
     * Method returns input stream.
     * @return InputStream.
     */
    Optional<InputStream> getInput();
}
