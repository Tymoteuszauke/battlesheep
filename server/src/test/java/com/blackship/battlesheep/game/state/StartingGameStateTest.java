package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.exceptions.WrongStateException;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Anna Gawda
 * @since 02.08.2017
 */
@Test
public class StartingGameStateTest {

    @Test(expectedExceptions = WrongStateException.class)
    public void shouldThrowAnException() throws WrongStateException {
        //given
        GameState givenGameState = new StartingGameState();

        //then
        assertNull(givenGameState.shotPositions());
    }

    @Test
    public void shouldReturnGameInProgress() {
        //given
        GameState givenGameState = new StartingGameState();
        //when
        givenGameState = givenGameState.changeState(new ArrayList<>(), new ArrayList<>());
        //then
        assertTrue(givenGameState instanceof GameStateInProgress);
    }
}