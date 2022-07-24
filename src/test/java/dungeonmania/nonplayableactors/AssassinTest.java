package dungeonmania.nonplayableactors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class AssassinTest {
    @Test
    public void testPlayerEntersAssassinReconRadius() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_assassinRecon", "c_assassinRecon");
        // pick up invisibility potion
        DungeonResponse dres = dmc.tick(Direction.RIGHT);
        // use invisibility potion
        // the assassin then moves in any of the cardinal directions
        assertDoesNotThrow(() -> dmc.tick(dres.getInventory().get(0).getId()));

        // teleports into recon range
        DungeonResponse res = dmc.tick(Direction.RIGHT);
        // in at most 3 ticks it should have encountered player and battled (assumption)
        assertThrows(InvalidActionException.class, () -> dmc.tick(dres.getInventory().get(0).getId()));
        assertThrows(InvalidActionException.class, () -> dmc.tick(dres.getInventory().get(0).getId()));
        assertThrows(InvalidActionException.class, () -> dmc.tick(dres.getInventory().get(0).getId()));

        res = dmc.tick(Direction.LEFT);

        assertFalse(res.getBattles().isEmpty());
    }

    @Test
    public void testPlayerLeavesAssassinReconRadius() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_leaveAssassinRecon", "c_assassinReconFive");
        // pick up invisibility potion
        DungeonResponse dres = dmc.tick(Direction.RIGHT);
        // use invisibility potion
        assertDoesNotThrow(() -> {
            long seed = (System.currentTimeMillis() / 100) * 100;
            // the assassin then moves in either left (1) or right (0)
            Random rng1 = new Random(seed);
            Position posBeforePot = TestUtils.getEntities(dres, "assassin").get(0).getPosition();
            DungeonResponse dr = dmc.tick(dres.getInventory().get(0).getId());
            int expectedXAfterPot = rng1.nextInt(2) == 1 ? posBeforePot.getX() - 1 : posBeforePot.getX() + 1;
            Position expectedPosAfterPot = new Position(expectedXAfterPot, posBeforePot.getY());
            Position actualPosAfterPot = TestUtils.getEntities(dr, "assassin").get(0).getPosition();
            assertEquals(expectedPosAfterPot, actualPosAfterPot);
        });

        // teleports into recon range
        DungeonResponse res = dmc.tick(Direction.RIGHT);
        Position posInRange = TestUtils.getEntities(res, "assassin").get(0).getPosition();
        // teleports out of range
        long seed = (System.currentTimeMillis() / 100) * 100;
        Random rng = new Random(seed);
        res = dmc.tick(Direction.LEFT);
        // the assassin then moves in either left (1) or right (0)
        int expectedXLeaveRecon = rng.nextInt(2) == 1 ? posInRange.getX() - 1 : posInRange.getX() + 1;
        Position expectedPosAfterPot = new Position(expectedXLeaveRecon, posInRange.getY());
        Position actualPosAfterPot = TestUtils.getEntities(res, "assassin").get(0).getPosition();
        assertEquals(expectedPosAfterPot, actualPosAfterPot);
        // in at most 3 ticks it should have encountered player and battled (assumption)

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        seed = (System.currentTimeMillis() / 100) * 100;
        rng = new Random(seed);
        res = dmc.tick(Direction.LEFT);
        // the assassin then moves in either left (1) or right (0)
        int expectedX = rng.nextInt(2) == 1 ? actualPosAfterPot.getX() - 1 : actualPosAfterPot.getX() + 1;
        Position expectedPos = new Position(expectedX, actualPosAfterPot.getY());
        Position actualPos = TestUtils.getEntities(res, "assassin").get(0).getPosition();
        assertEquals(expectedPos, actualPos);
    }

    @Test
    public void testAssassinInteractionWhiteBox() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_assassinInteract", "c_assassinReconFive");

        // pick up treasure
        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        assertDoesNotThrow(() -> {
            long seed = (System.currentTimeMillis() / 100) * 100;
            Random rng = new Random(seed);
            dmc.interact(TestUtils.getEntities(dres, "assassin").get(0).getId());
            int bribe_fail_rate = (int) Double.parseDouble(
                    TestUtils.getValueFromConfigFile("assassin_bribe_fail_rate", "c_assassinReconFive")) * 100;
            int numAllies = rng.nextInt(100) < bribe_fail_rate ? 0 : 1;
            Player player = DungeonManiaController.getDungeon().getPlayer();
            assertEquals(numAllies, player.getNumAllies());
        });
    }
}
