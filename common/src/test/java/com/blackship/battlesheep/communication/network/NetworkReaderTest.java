package com.blackship.battlesheep.communication.network;

import com.blackship.battlesheep.communication.Reader;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.testng.Assert.*;

/**
 * @author milosz
 * @since 28.07.2017
 */
public class NetworkReaderTest {

    @Test
    public void shouldReadCorrectStringFromStream() throws IOException {

        //given
        String expectedWord = "test";
        Reader givenReader = new NetworkReader(new ByteArrayInputStream(expectedWord.getBytes()));

        //when
        String givenWord = new String(givenReader.read());

        //then
        assertEquals(givenWord, expectedWord);
    }

}