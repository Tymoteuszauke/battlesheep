package com.blackship.battlesheep.game;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
@Test
public class GameManagerTest {
    private Game gameManager;

    @Test
    public void shouldCreateNewGameManager() {
        //given gameManager

        //when gameManager is created

        //then
        assertNotNull(gameManager);
    }

    @Test
    public void shouldReturnBoardFilledNumbers() {
        //given gameManager

        //when
        String givenBoardState = gameManager.boardsState();
        String expectedBoardState = TestUtils.generateEmptyBoardState();

        //then
        assertEquals(givenBoardState, expectedBoardState);
    }

    @BeforeTest
    public void setUp() {
        this.gameManager = new GameManager();
    }
}
