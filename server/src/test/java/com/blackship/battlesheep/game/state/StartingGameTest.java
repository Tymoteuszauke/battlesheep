package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.state.FieldState;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
@Test
public class StartingGameTest {

    private Game game;

    @BeforeTest
    public void setUp() {
        this.game = new StartingGame();
    }

    @Test
    public void shouldCreateNewGameManager() {
        //given game

        //when game is created

        //then
        assertNotNull(game);
    }

    @Test
    public void shouldReturnBoardFilledNumbers() {
        //given game

        //when
        String givenBoardState = game.boardsState();
        String expectedBoardState = TestUtils.generateBoardState(FieldState.EMPTY);

        //then
        assertEquals(givenBoardState, expectedBoardState);
    }

    @Test
    public void shouldReturnGameInProgress () {
        //given game

        //when

        //then
        assertNotNull(game.changeState(TestUtils::generateListWithNumbers));
    }
}
