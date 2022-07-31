package dungeonmania.nonplayableactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.getInventory;
import static dungeonmania.TestUtils.getEntities;

public class EnemyTest {

    @Nested
    public class testSpiderUnaffected {
        @Test
        public void testSpiderWalls() {
            DungeonManiaController controller = new DungeonManiaController();

            DungeonResponse response = controller.newGame("spider_unaffectedtest1", "c_spider_spawn_rate_0");
            Position current_position = getEntities(response, "spider").get(0).getPosition();

            assertEquals(current_position, new Position(3, 3));

            response = controller.tick(Direction.UP);
            current_position = getEntities(response, "spider").get(0).getPosition();
            assertEquals(current_position, new Position(3, 2));

            response = controller.tick(Direction.UP);
            current_position = getEntities(response, "spider").get(0).getPosition();
            assertEquals(current_position, new Position(4, 2));
        }

        @Test
        public void testSpiderDoors() {
            DungeonManiaController controller = new DungeonManiaController();

            DungeonResponse response = controller.newGame("spider_unaffectedtest2", "c_spider_spawn_rate_0");
            Position current_position = getEntities(response, "spider").get(0).getPosition();

            assertEquals(current_position, new Position(3, 3));

            response = controller.tick(Direction.UP);
            current_position = getEntities(response, "spider").get(0).getPosition();
            assertEquals(current_position, new Position(3, 2));

            response = controller.tick(Direction.UP);
            current_position = getEntities(response, "spider").get(0).getPosition();
            assertEquals(current_position, new Position(4, 2));

        }

        @Test
        public void testSpiderPortals() {
            DungeonManiaController controller = new DungeonManiaController();

            DungeonResponse response = controller.newGame("spider_unaffectedtest3", "c_spider_spawn_rate_0");
            Position current_position = getEntities(response, "spider").get(0).getPosition();

            assertEquals(current_position, new Position(3, 3));

            response = controller.tick(Direction.UP);
            current_position = getEntities(response, "spider").get(0).getPosition();
            assertEquals(current_position, new Position(3, 2));

            response = controller.tick(Direction.UP);
            current_position = getEntities(response, "spider").get(0).getPosition();
            assertEquals(current_position, new Position(4, 2));
        }

        @Test
        public void testSpiderSwitches() {
            DungeonManiaController controller = new DungeonManiaController();

            DungeonResponse response = controller.newGame("spider_unaffectedtest4", "c_spider_spawn_rate_0");
            Position current_position = getEntities(response, "spider").get(0).getPosition();

            assertEquals(current_position, new Position(3, 3));

            response = controller.tick(Direction.UP);
            current_position = getEntities(response, "spider").get(0).getPosition();
            assertEquals(current_position, new Position(3, 2));

            response = controller.tick(Direction.UP);
            current_position = getEntities(response, "spider").get(0).getPosition();
            assertEquals(current_position, new Position(4, 2));
        }

        @Test
        public void testSpiderExits() {
            DungeonManiaController controller = new DungeonManiaController();

            DungeonResponse response = controller.newGame("spider_unaffectedtest5", "c_spider_spawn_rate_0");
            Position current_position = getEntities(response, "spider").get(0).getPosition();

            assertEquals(current_position, new Position(3, 3));

            response = controller.tick(Direction.UP);
            current_position = getEntities(response, "spider").get(0).getPosition();
            assertEquals(current_position, new Position(3, 2));

            response = controller.tick(Direction.UP);
            current_position = getEntities(response, "spider").get(0).getPosition();
            assertEquals(current_position, new Position(4, 2));
        }

        @Test
        public void testSpiderSpawner() {
            DungeonManiaController controller = new DungeonManiaController();
            controller.newGame("spidertests/d_spawnerSpider", "c_noSpawns");
            DungeonResponse dres = controller.tick(Direction.UP);
            // spider should move down since they can move on walls
            assertEquals(TestUtils.getEntities(dres, "zombie_toast_spawner").get(0).getPosition(),
                    TestUtils.getEntities(dres, "spider").get(0).getPosition());
        }
    }

    @Test
    public void testMercenaryMovesTowardsPlayer() {
        DungeonManiaController controller = new DungeonManiaController();

        DungeonResponse response = controller.newGame("d_mercenary_move_player_test", "c_spider_spawn_rate_0");
        Position current_position = getEntities(response, "mercenary").get(0).getPosition();
        assertEquals(current_position, new Position(6, 1));

        response = controller.tick(Direction.LEFT);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        assertEquals(current_position, new Position(5, 1));

        response = controller.tick(Direction.LEFT);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        assertEquals(current_position, new Position(4, 1));

        response = controller.tick(Direction.LEFT);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        assertEquals(current_position, new Position(3, 1));

        response = controller.tick(Direction.LEFT);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        assertEquals(current_position, new Position(2, 1));
    }

    @Test
    public void testMercAndZombieMoveAwayInvincible() throws IllegalArgumentException, InvalidActionException {
        DungeonManiaController controller = new DungeonManiaController();

        DungeonResponse response = controller.newGame("d_player_invincible_test", "c_long_potion_duration");
        Position current_position = getEntities(response, "mercenary").get(0).getPosition();
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        Position player_position = getPlayer(response).get().getPosition();
        Integer distance = check_distance(current_position, player_position);
        assertEquals(5, distance);

        // closer
        response = controller.tick(Direction.RIGHT);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        player_position = getPlayer(response).get().getPosition();
        distance = check_distance(current_position, player_position);
        assertEquals(3, distance);

        // away
        String itemUsedId = getInventory(response, "invincibility_potion").get(0).getId();
        response = controller.tick(itemUsedId);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        player_position = getPlayer(response).get().getPosition();
        distance = check_distance(current_position, player_position);
        assertEquals(4, distance);

        // away (move relatively stationary, so no change on distance)
        response = controller.tick(Direction.RIGHT);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        player_position = getPlayer(response).get().getPosition();
        distance = check_distance(current_position, player_position);
        assertEquals(4, distance);

        // away
        response = controller.tick(Direction.RIGHT);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        player_position = getPlayer(response).get().getPosition();
        distance = check_distance(current_position, player_position);
        assertEquals(4, distance);

        // away
        response = controller.tick(Direction.RIGHT);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        player_position = getPlayer(response).get().getPosition();
        distance = check_distance(current_position, player_position);
        assertEquals(4, distance);
    }

    @Test
    public void testMercMoveRandomPlayerInvis() throws IllegalArgumentException, InvalidActionException {
        DungeonManiaController controller = new DungeonManiaController();

        DungeonResponse response = controller.newGame("d_player_invisible_test", "c_long_potion_duration");
        Position current_position = getEntities(response, "mercenary").get(0).getPosition();
        assertEquals(current_position, new Position(6, 1));

        response = controller.tick(Direction.RIGHT);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        assertEquals(current_position, new Position(5, 1));

        String itemUsedId = getInventory(response, "invisibility_potion").get(0).getId();
        response = controller.tick(itemUsedId);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        assertNotEquals(current_position, new Position(5, 1));
    }

    @Test
    public void testMercAndZombieMovementBackToDefault() throws IllegalArgumentException, InvalidActionException {
        DungeonManiaController controller = new DungeonManiaController();

        DungeonResponse response = controller.newGame("d_player_invincible_test", "simple");
        Position current_position = getEntities(response, "mercenary").get(0).getPosition();
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        Position player_position = getPlayer(response).get().getPosition();
        Integer distance = check_distance(current_position, player_position);
        assertEquals(5, distance);

        // closer
        response = controller.tick(Direction.RIGHT);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        player_position = getPlayer(response).get().getPosition();
        distance = check_distance(current_position, player_position);
        assertEquals(3, distance);

        // away
        String itemUsedId = getInventory(response, "invincibility_potion").get(0).getId();
        response = controller.tick(itemUsedId);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        player_position = getPlayer(response).get().getPosition();
        distance = check_distance(current_position, player_position);
        assertEquals(4, distance);

        // closer (back to default behavior)
        response = controller.tick(Direction.RIGHT);
        current_position = getEntities(response, "mercenary").get(0).getPosition();
        player_position = getPlayer(response).get().getPosition();
        distance = check_distance(current_position, player_position);
        assertEquals(2, distance);
    }

    @Test
    public void testZombiesUnaffectedPortals() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse response = controller.newGame("d_zombie_visit_portal", "simple");
        List<Position> range = getEntities(response, "zombie_toast")
                .get(0).getPosition().getAdjacentPositions();

        response = controller.tick(Direction.UP);
        // zombie is surrounded by portal
        // it will still in a range if not teleported
        Position current_zombie_position = getEntities(response, "zombie_toast").get(0).getPosition();
        assertTrue(range.stream().anyMatch(position -> position.equals(current_zombie_position)));
    }

    // helper function
    private Integer check_distance(Position a, Position b) {
        Position tmp = Position.calculatePositionBetween(a, b);
        Integer distance = Math.abs(tmp.getX()) + Math.abs(tmp.getY());
        return distance;
    }

}
