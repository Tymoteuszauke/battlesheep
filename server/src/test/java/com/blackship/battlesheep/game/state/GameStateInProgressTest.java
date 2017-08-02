package com.blackship.battlesheep.game.state;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Anna Gawda
 * @since 02.08.2017
 */
@Test
public class GameStateInProgressTest {

    @DataProvider
    private Object[][] listOfShips() {
        return new Object[][] {
                {TestUtils.hardcodeShips()}
        };
    }

    @DataProvider
    private Object[][] listOfShipsAndPositionsToShoot() {
        return new Object[][] {
                {TestUtils.hardcodeShips(), Arrays.asList(12, 22, 32, 42)}
        };
    }

    @Test(dataProvider = "listOfShips")
    public void shouldReturnGameInProgress(List<List<Integer>> givenShipPositions) {
        //given
        GameState givenGameState = new GameStateInProgress(givenShipPositions, givenShipPositions);
        //when
        givenGameState = givenGameState.changeState(Arrays.asList(Arrays.asList()),Arrays.asList(Arrays.asList()));
        //then
        assertTrue(givenGameState instanceof GameStateInProgress);
    }

    @Test(dataProvider = "listOfShipsAndPositionsToShoot")
    public void shouldReturnListOfShotPositions(List<List<Integer>> givenShipPositions, List<Integer> givenShotPositions) {
        //given
        GameState givenGameState = new GameStateInProgress(givenShipPositions, givenShipPositions);
        List<List<Integer>> givenPlayerPositions = new ArrayList<>();
        givenPlayerPositions.add(givenShotPositions);

        //when
        givenGameState.changeState(givenPlayerPositions, givenPlayerPositions);

        //then
        assertEquals(givenGameState.shotPositions().get(0).size(), givenShotPositions.size());
        assertTrue(givenGameState.shotPositions().get(0).containsAll(givenShotPositions));
    }
}