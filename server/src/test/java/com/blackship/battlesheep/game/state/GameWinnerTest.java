package com.blackship.battlesheep.game.state;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Anna Gawda
 * @since 04.08.2017
 */
@Test
public class GameWinnerTest {

    @DataProvider
    private Object[][] gameWinnerStringsAndValues() {

        return new Object[][] {
                {GameWinner.PLAYER_ONE, "First player won"},
                {GameWinner.PLAYER_TWO, "Second player won"},
                {GameWinner.BOTH_PLAYERS, "Draw"},
                {GameWinner.NONE, ""}
        };
    }

    @Test(dataProvider = "gameWinnerStringsAndValues")
    public void shouldReturnCorrectStrings(GameWinner givenValue, String expectedMessage) {

        assertEquals(givenValue.toString(), expectedMessage);
    }

    @DataProvider
    private Object[][] gameWinnerValuesAndStrings() {
        return new Object[][] {
                {"PLAYER_ONE", GameWinner.PLAYER_ONE},
                {"PLAYER_TWO", GameWinner.PLAYER_TWO},
                {"BOTH_PLAYERS", GameWinner.BOTH_PLAYERS},
                {"NONE", GameWinner.NONE}
        };
    }

    @Test(dataProvider = "gameWinnerValuesAndStrings")
    public void shouldReturnCorrectValues(String givenString, GameWinner expectedValue) {

        assertEquals(GameWinner.valueOf(givenString), expectedValue);
    }
}