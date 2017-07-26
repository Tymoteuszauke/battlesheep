package com.blackship.battlesheep.common.communication;

import java.io.IOException;

public interface Writer {
    void write(byte[] bytes) throws IOException;
}
