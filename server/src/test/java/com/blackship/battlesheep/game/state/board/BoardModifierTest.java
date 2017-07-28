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
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
@Test
public class BoardModifierTest {

    private Board board;

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

    @BeforeTest
    public void setupBoard() {
        board = new StartingBoard();
    }

    @Test(dataProvider = "boardPositions")
    public void shouldInsertPositionOnBoard(List<Integer> givenShip) {
        //given StartingBoard

        //when
        Board givenBoard  = BoardModifier.insertShip(board, givenShip);

        //then
        //TODO givenBoard.getPositions().get(givenShip.get(ship)) to one method
        IntStream.rangeClosed(0, 3)
                .forEach(ship -> assertEquals(
                        givenBoard.getPositions().get(givenShip.get(ship)),
                        new Position(givenShip.get(ship), FieldState.TAKEN))
                );
    }

    @Test(dataProvider = "boardShips")
    public void shouldInsertShipOnBoard(List<Ship> givenShips) {
        //given StartingBoard

        //when
        Board givenBoard = BoardModifier.insertShips(givenShips);
        //then
        assertEquals(givenBoard.getPositions().get(1), new Position(1, FieldState.TAKEN));
    }
}