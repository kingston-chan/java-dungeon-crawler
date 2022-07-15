package dungeonmania.movementtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.getEntities;

public class MovementTowardPlayer {
    @Test
    public void testMercenaryPortal(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_enemy_portal_test", "simple");

        // protal: 2,1
        // portal position: 3,3
        // mercenary position: 6,5
        controller.tick(Direction.UP);
        controller.tick(Direction.UP);
        controller.tick(Direction.UP);
        DungeonResponse current_dungeon = controller.tick(Direction.UP);

        EntityResponse player = getPlayer(current_dungeon).get();
        assertEquals(player.getPosition(), new Position(1, -3));

        // mercenary notify portal and it will get closer to player if use portal
        EntityResponse mercenary = getEntities(current_dungeon, "mercenary").get(0);
        assertEquals(mercenary.getPosition(), new Position(4, 3));

        // mercenary teleported by portal
        current_dungeon =  controller.tick(Direction.UP);
        mercenary = getEntities(current_dungeon, "mercenary").get(0);
        assertEquals(mercenary.getPosition(), new Position(2, 0));
    }

    @Test
    public void test_no_player_in_map(){
        // DungeonManiaController controller = new DungeonManiaController();
        // controller.newGame("d_test_no_player_movement_to_player", "simple");
    }
}
