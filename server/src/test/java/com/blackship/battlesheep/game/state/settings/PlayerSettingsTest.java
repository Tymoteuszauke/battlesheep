package com.blackship.battlesheep.game.state.settings;

import com.blackship.battlesheep.game.state.TestUtils;
import com.blackship.battlesheep.game.state.board.BoardModifier;
import com.blackship.battlesheep.game.state.fleet.Fleet;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

/**
 * @author Anna Gawda
 * @since 31.07.2017
 */
@Test
public class PlayerSettingsTest {

    @Test
    public void shouldReturnPlayersBoard() {
        //given
        PlayerSettings playerSettings = new PlayerSettings(BoardModifier.insertPositions(TestUtils.generateEmptyList()),
                new Fleet(TestUtils.generateShipList()));

        //then
        assertFalse(playerSettings.isBoardEmpty());
        assertFalse(playerSettings.isFleetSunk());
    }
}