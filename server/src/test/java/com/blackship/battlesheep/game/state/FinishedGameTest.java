package com.blackship.battlesheep.game.state;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Anna Gawda
 * @since 30.07.2017
 */
@Test
public class FinishedGameTest {
    @Test
    public void shouldReturnGameFinished() {
        //given
        Game game = new FinishedGame();
        //when
        String givenBoard = game.boardsState();
        String expectedBoard = "Game finished";
        //then
        assertEquals(givenBoard, expectedBoard);
    }

    @Test
    public void shouldReturnNewStartingGame () {
        //given
        Game game = new FinishedGame();
        //when

        //then
        assertTrue(game.changeState(TestUtils::generateEmptyList) instanceof StartingGame);
    }
}