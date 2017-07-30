package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.board.BoardModifier;
import com.blackship.battlesheep.game.state.state.FieldState;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Anna Gawda
 * @since 30.07.2017
 */
@Test
public class GameInProgressTest {
    @Test
    public void shouldReturnTakenFields () {
        //given
        Game game = new GameInProgress(BoardModifier.insertPositions(TestUtils.generateListWithNumbers()));
        //when
        String givenBoard = game.boardsState();
        String expectedBoard = TestUtils.generateBoardState(FieldState.TAKEN);
        //then
        assertEquals(givenBoard, expectedBoard);
    }

    @Test
    public void shouldReturnEmptyString () {
        //given
        Game game = new GameInProgress(BoardModifier.insertPositions(TestUtils.generateEmptyList()));
        //when
        String givenBoard = game.boardsState();
        String expectedBoard = TestUtils.generateBoardState(FieldState.EMPTY);
        //then
        assertEquals(givenBoard, expectedBoard);
    }

    @Test
    public void shouldReturnFinishedGame () {
        //given
        Game game = new GameInProgress(BoardModifier.insertPositions(TestUtils.generateListWithNumbers()));
        //when

        //then
        assertTrue(game.changeState(TestUtils::generateEmptyList) instanceof FinishedGame);
    }

    @Test
    public void shouldReturnGameInProgress () {
        //given
        Game game = new GameInProgress(BoardModifier.insertPositions(TestUtils.generateListWithNumbers()));
        //when

        //then
        assertTrue(game.changeState(TestUtils::generateListWithNumbers) instanceof GameInProgress);
    }
}