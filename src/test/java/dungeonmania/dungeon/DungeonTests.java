package dungeonmania.dungeon;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.getInventory;
import static dungeonmania.TestUtils.getGoals;
import static dungeonmania.TestUtils.countEntityOfType;
import static dungeonmania.TestUtils.getValueFromConfigFile;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

/**
 * Dungeon tests
 * 
 * @author Kingston Chan
 */
public class DungeonTests {
    @Nested
    public class DungeonUnitWhiteBoxTests {
        @Test
        public void testInitialiseDungeon() {

        }

        @Test
        public void testGetConfigFile() {

        }

        @Test
        public void testGetGoals() {

        }

        @Test
        public void testGetBattles() {

        }
    }

    @Nested
    public class DungeonUnitBlackBoxTests {
        @Test
        public void testGetDungeonResponse() {

        }
    }
}
