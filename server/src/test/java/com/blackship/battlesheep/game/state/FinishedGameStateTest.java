package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.exceptions.WrongStateException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Anna Gawda
 * @since 04.08.2017
 */
@Test
public class FinishedGameStateTest {

    @DataProvider
    private Object[][] winningAndNotWinningMovesAndExpectedMessages() {
        return new Object[][] {
                {TestUtils.getWinningMoves(), TestUtils.getWinningMoves(), GameWinner.BOTH_PLAYERS},
                {TestUtils.getWinningMoves(), TestUtils.getNotWinningMoves(), GameWinner.PLAYER_ONE},
                {TestUtils.getNotWinningMoves(), TestUtils.getWinningMoves(), GameWinner.PLAYER_TWO}
        };
    }

    @Test(dataProvider = "winningAndNotWinningMovesAndExpectedMessages")
    public void shouldReturnCorrectWinner(List<List<Integer>> playerOneMoves, List<List<Integer>> playerTwoMoves, GameWinner expectedState) {

        //given
        GameState givenGameState = new GameStateInProgress(TestUtils.hardcodeShips(), TestUtils.hardcodeShips());

        //when
        FinishedGameState givenFinishedGameState = (FinishedGameState)
                givenGameState.changeState(playerOneMoves, playerTwoMoves);
        //then
        assertEquals(givenFinishedGameState.getWinner(), expectedState.toString());
    }

    @Test
    public void shouldReturnFinishedGame() {
        FinishedGameState givenFinishedGameState = new FinishedGameState("", null, null);

        assertEquals(givenFinishedGameState.changeState(null, null).getClass(), FinishedGameState.class);
    }
}