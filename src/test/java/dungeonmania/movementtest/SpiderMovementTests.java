package dungeonmania.movementtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static dungeonmania.TestUtils.getEntities;

public class SpiderMovementTests {
    @Test
    public void testSpiderNormalPath() {
        DungeonManiaController controller = new DungeonManiaController();

        DungeonResponse response = controller.newGame("d_test_spider_movement", "c_spider_spawn_rate_0");
        Position current_positoin = getEntities(response, "spider").get(0).getPosition();
        assertEquals(current_positoin, new Position(3, 3));

        response = controller.tick(Direction.UP);
        current_positoin = getEntities(response, "spider").get(0).getPosition();
        assertEquals(current_positoin, new Position(3, 2));

        response = controller.tick(Direction.UP);
        current_positoin = getEntities(response, "spider").get(0).getPosition();
        assertEquals(current_positoin, new Position(4, 2));

        response = controller.tick(Direction.UP);
        current_positoin = getEntities(response, "spider").get(0).getPosition();
        assertEquals(current_positoin, new Position(4, 3));

        response = controller.tick(Direction.UP);
        current_positoin = getEntities(response, "spider").get(0).getPosition();
        assertEquals(current_positoin, new Position(4, 4));

        response = controller.tick(Direction.UP);
        current_positoin = getEntities(response, "spider").get(0).getPosition();
        assertEquals(current_positoin, new Position(3, 4));

        response = controller.tick(Direction.UP);
        current_positoin = getEntities(response, "spider").get(0).getPosition();
        assertEquals(current_positoin, new Position(2, 4));

        response = controller.tick(Direction.UP);
        current_positoin = getEntities(response, "spider").get(0).getPosition();
        assertEquals(current_positoin, new Position(2, 3));

        response = controller.tick(Direction.UP);
        current_positoin = getEntities(response, "spider").get(0).getPosition();
        assertEquals(current_positoin, new Position(2, 2));

        response = controller.tick(Direction.UP);
        current_positoin = getEntities(response, "spider").get(0).getPosition();
        assertEquals(current_positoin, new Position(3, 2));
        // a loop here
    }

    @Test
    public void testSpiderBoulderOnUpOfSpiderSpawn() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spider_test1", "simple");
        controller.tick(Direction.UP);
        DungeonResponse current_dungeon = controller.tick(Direction.UP);
        Optional<EntityResponse> tmp = current_dungeon.getEntities().stream()
                .filter(entity -> entity.getType().equals("spider")).findFirst();
        EntityResponse spider = tmp.get();
        assertEquals(new Position(3, 3), spider.getPosition());
    }

    @Test
    public void testSpiderBoulderOnPath() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spider_test2", "simple");
        controller.tick(Direction.DOWN);
        DungeonResponse current_dungeon = controller.tick(Direction.UP);
        Optional<EntityResponse> tmp = current_dungeon.getEntities().stream()
                .filter(entity -> entity.getType().equals("spider")).findFirst();
        EntityResponse spider = tmp.get();
        assertEquals(new Position(2, 2), spider.getPosition());

        controller.tick(Direction.UP);
        controller.tick(Direction.UP);
        controller.tick(Direction.UP);
        controller.tick(Direction.UP);
        controller.tick(Direction.UP);

        current_dungeon = controller.tick(Direction.UP);
        spider = current_dungeon.getEntities().stream()
                .filter(entity -> entity.getType().equals("spider")).findFirst().get();
        // should alternate direction because blocked by boulder
        assertEquals(new Position(4, 4), spider.getPosition());

    }

    @Test
    public void testTwoBoulderStuckSpider() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spider_test3", "simple");
        controller.tick(Direction.DOWN);
        DungeonResponse current_dungeon = controller.tick(Direction.UP);
        Optional<EntityResponse> tmp = current_dungeon.getEntities().stream()
                .filter(entity -> entity.getType().equals("spider")).findFirst();
        EntityResponse spider = tmp.get();
        assertEquals(new Position(3, 2), spider.getPosition());
    }
}
