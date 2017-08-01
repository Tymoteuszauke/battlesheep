package com.blackship.battlesheep.game.state.board;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Anna Gawda
 * @since 01.08.2017
 */
@Test
public class BoardSettingsTest {

    @Test
    public void shouldReturnBoardValues() {
        //then
        assertEquals(BoardSettings.BOARD_SIZE.getValue(), 100);
        assertEquals(BoardSettings.BOARD_STARTING_FIELD.getValue(), 1);
        assertEquals(BoardSettings.BOARD_WIDTH.getValue(), 10);
    }

    @Test
    public void shouldReturnStringValues() {
        //then
        assertEquals(BoardSettings.valueOf("BOARD_SIZE"), BoardSettings.BOARD_SIZE);
        assertEquals(BoardSettings.valueOf("BOARD_STARTING_FIELD"), BoardSettings.BOARD_STARTING_FIELD);
        assertEquals(BoardSettings.valueOf("BOARD_WIDTH"), BoardSettings.BOARD_WIDTH);
    }
}