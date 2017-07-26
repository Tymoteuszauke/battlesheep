package com.blackship.battlesheep.common.communication;

import java.io.IOException;

public interface Reader {
    byte[] read() throws IOException;
}
