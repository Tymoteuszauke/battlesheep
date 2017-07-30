package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.state.TestUtils;
import com.blackship.battlesheep.game.state.state.FieldState;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
@Test
public class StartingBoardTest {

    private Board startingBoard;

    @BeforeTest
    public void setUp() {
        this.startingBoard = new StartingBoard();
    }

    @DataProvider
    private Object[][] fieldNumbers() {
        return new Object[][] {
                {1},
                {23},
                {46}
        };
    }

    @Test
    public void shouldReturnStartingBoard() {
        //given startingBoard

        //when created new StartingBoard

        //then
        assertNotNull(startingBoard);
    }

    @Test
    public void shouldReturnBoardFieldSymbols() {
        //given startingBoard

        //when
        String givenBoardState = startingBoard.toString();
        String expectedBoardState = TestUtils.generateBoardState(FieldState.EMPTY);

        //then
        assertEquals(givenBoardState, expectedBoardState);
    }

    @Test(dataProvider = "fieldNumbers")
    public void shouldReturnEmptyFieldState(Integer fieldNumber) {
        //given startingBoard

        //when

        //then
        assertEquals(startingBoard.getPositionState(fieldNumber), FieldState.EMPTY);
    }
}
