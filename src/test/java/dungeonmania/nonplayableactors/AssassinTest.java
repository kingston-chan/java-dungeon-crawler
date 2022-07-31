package dungeonmania.nonplayableactors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

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
        // in at most 4 ticks it should have encountered player and battled (assumption)
        assertThrows(InvalidActionException.class, () -> dmc.tick(dres.getInventory().get(0).getId()));
        assertThrows(InvalidActionException.class, () -> dmc.tick(dres.getInventory().get(0).getId()));
        assertThrows(InvalidActionException.class, () -> dmc.tick(dres.getInventory().get(0).getId()));
        assertThrows(InvalidActionException.class, () -> dmc.tick(dres.getInventory().get(0).getId()));

        res = dmc.tick(Direction.LEFT);

        assertFalse(res.getBattles().isEmpty());
    }

    @Test
    public void testAssassinInteractionWhiteBox() {
        DungeonManiaController dmc = new DungeonManiaController();

        int numSuccess = 0;
        int numTrials = 500000;

        double bribe_fail_rate = Double.parseDouble(
                TestUtils.getValueFromConfigFile("assassin_bribe_fail_rate", "c_assassinRecon"));

        for (int i = 0; i < numTrials; i++) {
            dmc.newGame("d_assassinInteract", "c_assassinRecon");

            Player player = DungeonManiaController.getDungeon().getPlayer();
            DungeonManiaController.getDungeon().getNonPlayableActors()
                    .forEach(o -> ((Mercenary) o).recruitedBy(player));
            numSuccess += player.getNumAllies();
        }

        // buffer for fail rate of 0.001
        assertTrue((bribe_fail_rate * numTrials) >= ((numTrials - numSuccess) / 500) * 500.0);
    }

    @Test
    public void testAssassinAlwaysSuccessfulInteractionWhiteBox() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_assassinInteract", "c_assassinAlwaysSucceedBribe");

        // pick up treasure
        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        assertDoesNotThrow(() -> dmc.interact(TestUtils.getEntities(dres, "assassin").get(0).getId()));
        Player player = DungeonManiaController.getDungeon().getPlayer();
        assertEquals(1, player.getNumAllies());
    }

    @Test
    public void testAssassinAlwaysUnsuccessfulInteractionWhiteBox() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_assassinInteract", "c_assassinAlwaysFailBribe");

        // pick up treasure
        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        assertDoesNotThrow(() -> {
            DungeonResponse res = dmc.interact(TestUtils.getEntities(dres, "assassin").get(0).getId());
            assertTrue(res.getInventory().isEmpty());
        });

        Player player = DungeonManiaController.getDungeon().getPlayer();
        assertEquals(0, player.getNumAllies());
    }
}
