package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.board.Board;
import com.blackship.battlesheep.game.state.board.BoardModifier;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
@Test
public class StartingGameTest {

    private Game game;

    @BeforeTest
    public void setUp() {
        this.game = new StartingGame(BoardModifier.insertPositions(TestUtils.generateEmptyList()));
    }

    @Test
    public void shouldReturnBoardEmptyFields() {
        //given game

        //when
        Map<Integer, Board> givenBoardState = game.boardsState();

        //then
        assertFalse(givenBoardState.isEmpty());
    }

    @Test
    public void shouldReturnGameInProgress () {
        //given game

        //then
        assertTrue(game.changeState(TestUtils::generateListWithNumbers) instanceof GameInProgress);
    }
}
