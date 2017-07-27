package com.blackship.battlesheep.game.state.board;

import com.blackship.battlesheep.game.FieldState;
import com.blackship.battlesheep.game.Position;
import com.blackship.battlesheep.game.state.fleet.Ship;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
@Test
public class BoardModifierTest {

    private Board board;

    @DataProvider
    private Object[][] boardSheepData() {
        return new Object[][] {
                {new ArrayList<>(Arrays.asList(1, 11, 12, 13))},
                {new ArrayList<>(Arrays.asList(22, 23, 24, 34))},
                {new ArrayList<>(Arrays.asList(1, 11 , 21, 31))}
        };
    }

    @DataProvider
    private Object[][] boardSheepsData() {
        return new Object[][] {
                {}
        };
    }

    @BeforeTest
    public void setupBoard() {
        board = new StartingBoard();
    }

    @Test(dataProvider = "boardSheepData")
    public void insertShipExpectBoardWithInsertedShip(List<Integer> givenShip) {
        //given StartingBoard

        //when
        Board givenBoard  = BoardModifier.insertShip(board, givenShip);
        //then
        assertEquals(givenBoard.getPositions().get(givenShip.get(0)), new Position(givenShip.get(0), FieldState.TAKEN));
        assertEquals(givenBoard.getPositions().get(givenShip.get(1)), new Position(givenShip.get(1), FieldState.TAKEN));
        assertEquals(givenBoard.getPositions().get(givenShip.get(2)), new Position(givenShip.get(2), FieldState.TAKEN));
        assertEquals(givenBoard.getPositions().get(givenShip.get(3)), new Position(givenShip.get(3), FieldState.TAKEN));
    }

    @Test(dataProvider = "boardSheepsData")
    public void insertShipsExpectBoardContainingShips(List<Ship> givenShips) {
        //given StartingBoard

        //when
        Board givenBoard = BoardModifier.insertShips(board, givenShips);
        //then


        assertEquals(givenBoard.getShip(givenShips.get(0)), givenShips.get(0));
        assertEquals(givenBoard.getShip(givenShips.get(1)), givenShips.get(1));
        assertEquals(givenBoard.getShip(givenShips.get(2)), givenShips.get(2));
    }



}