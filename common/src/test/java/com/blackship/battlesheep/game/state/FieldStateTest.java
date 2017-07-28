package com.blackship.battlesheep.game.state;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Anna Gawda
 * @since 28.07.2017
 */
@Test
public class FieldStateTest {
    @Test
    public void shouldReturnCorrectSymbol() {
        //given
        String expectedSymbolEmpty = "O";
        String expectedSymbolTaken = "-";
        //when

        //then
        assertEquals(FieldState.EMPTY.toString(), expectedSymbolEmpty);
        assertEquals(FieldState.TAKEN.toString(), expectedSymbolTaken);
    }

    @Test
    public void shouldReturnCorrectStateFromString() {
        //given
        String givenStateStringEmpty = "EMPTY";
        String givenStateStringTaken = "TAKEN";
        //when

        //then
        assertEquals(FieldState.valueOf(givenStateStringEmpty), FieldState.EMPTY);
        assertEquals(FieldState.valueOf(givenStateStringTaken), FieldState.TAKEN);
    }
}