package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.exceptions.FirstPlayerWon;
import com.blackship.battlesheep.game.state.exceptions.SecondPlayerWon;
import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;
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

    private final static Logger log = LogUtils.getLogger();

    @Test
    public void shouldReturnNull() {
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
        try {
            givenGameState = givenGameState.changeState(new ArrayList<>(), new ArrayList<>());
        } catch (FirstPlayerWon firstPlayerWon) {
            log.info("First player won.");
        } catch (SecondPlayerWon secondPlayerWon) {
            log.info("Second player won.");
        }
        //then
        assertTrue(givenGameState instanceof GameStateInProgress);
    }
}