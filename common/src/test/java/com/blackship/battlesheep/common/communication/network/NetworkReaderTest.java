package com.blackship.battlesheep.common.communication.network;

import com.blackship.battlesheep.common.communication.Reader;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.testng.Assert.*;

public class NetworkReaderTest {

    @Test
    public void readAndExpectGivenStringTest() throws IOException {

        //given
        String expectedWord = "test";
        Reader givenReader = new NetworkReader(new ByteArrayInputStream(expectedWord.getBytes()));

        //when
        String givenWord = new String(givenReader.read());

        //then
        assertEquals(givenWord, expectedWord);
    }

}