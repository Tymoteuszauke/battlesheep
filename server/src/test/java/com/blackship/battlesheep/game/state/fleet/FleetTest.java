package com.blackship.battlesheep.game.state.fleet;

import com.blackship.battlesheep.game.state.TestUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

/**
 * @author Anna Gawda
 * @since 31.07.2017
 */
@Test
public class FleetTest {

    @Test
    public void shouldReturnFalseForAliveShip() {
        //given
        Fleet fleet = new Fleet(TestUtils.generateShipList());

        //then
        assertFalse(fleet.isSunk());
    }
}