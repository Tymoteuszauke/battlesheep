package com.blackship.battlesheep.game.state;

import com.blackship.battlesheep.game.state.board.Board;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Anna Gawda
 * @since 30.07.2017
 */
@Test
public class FinishedGameTest {
    @Test
    public void shouldReturnGameFinished() {
        //given
        Game game = new FinishedGame();
        //when
        Map<Integer, Board> givenBoard = game.boardsState();
        //then
        assertTrue(givenBoard.isEmpty());
    }

    @Test
    public void shouldReturnNewStartingGame () {
        //given
        Game game = new FinishedGame();
        //when

        //then
        assertTrue(game.changeState(TestUtils::generateEmptyList) instanceof StartingGame);
    }
}