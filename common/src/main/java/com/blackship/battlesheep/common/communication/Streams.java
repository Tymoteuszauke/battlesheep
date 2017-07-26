package com.blackship.battlesheep.common.communication;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public interface Streams {
    Optional<OutputStream> getOutput();
    Optional<InputStream> getInput();
}
