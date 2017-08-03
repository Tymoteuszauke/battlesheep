package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.state.TestUtils;
import com.blackship.battlesheep.game.state.fleet.Ship;
import com.blackship.battlesheep.game.state.state.FieldState;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */

@Test
public class BoardModifierTest {

    private Board board;

    @BeforeTest
    public void setupBoard() {
        board = new StartingBoard();
    }

    @DataProvider
    private Object[][] boardPositions() {
        return new Object[][] {
                {new ArrayList<>(Arrays.asList(1, 11, 12, 13))},
                {new ArrayList<>(Arrays.asList(1, 11 , 21, 31))}
        };
    }

    @DataProvider
    private Object[][] boardShips() {
        return new Object[][] {
                {new ArrayList<>(Arrays.asList(new Ship(new ArrayList<>(Arrays.asList(1, 11, 21)))))}
        };
    }

    @Test(dataProvider = "boardPositions")
    public void shouldInsertPositionOnBoard(List<Integer> givenShip) {
        //given StartingBoard

        //when
        Board givenBoard  = BoardModifier.insertShip(board, givenShip);
        //then
        IntStream.rangeClosed(0, 3)
                .forEach(shipPosition ->
                        Assert.assertEquals(givenBoard.getPositionState(givenShip.get(shipPosition)), FieldState.TAKEN));
    }

    @Test
    public void shouldInsertPositionsOnBoard () {
        //given
        Board givenBoard = BoardModifier.initialBoard(TestUtils.generateListWithNumbers());
        //when
        String givenLayout = givenBoard.toString();
        String expectedLayout = TestUtils.generateBoardState(FieldState.TAKEN);
        //then
        assertEquals(givenLayout, expectedLayout);
    }
}