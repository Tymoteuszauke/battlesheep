package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.TestUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
@Test
public class StartingBoardTest {
    private Board startingBoard;

    @BeforeTest
    public void setUp() {
        this.startingBoard = new StartingBoard();
    }

    @Test
    public void shouldReturnStartingBoardNotNull() {
        //given startingBoard

        //when created new StartingBoard

        //then
        assertNotNull(startingBoard);
    }

    @Test
    public void shouldReturnBoardFieldNumbers() {
        //given startingBoard

        //when
        String givenBoardState = startingBoard.boardLayout();
        String expectedBoardState = TestUtils.generateEmptyBoardState();
        //then
        assertEquals(givenBoardState, expectedBoardState);
    }
}
