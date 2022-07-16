package dungeonmania.movementtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static dungeonmania.TestUtils.getEntities;


public class SpiderWhiteBoxTest {
    @Test
    public void testSpiderNormalPath() {
        DungeonManiaController controller = new DungeonManiaController();

        DungeonResponse response = controller.newGame("d_test_spider_movement", "simple");
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
        controller.newGame("spider_white_box_test1", "simple");
        controller.tick(Direction.UP);
        DungeonResponse current_dungeon = controller.tick(Direction.UP);
        Optional<EntityResponse> tmp = current_dungeon.getEntities().stream().filter(entity -> entity.getType().equals("spider")).findFirst();
        EntityResponse spider = tmp.get();
        assertEquals(spider.getPosition(), new Position(3,3));
    }

    @Test
    public void testSpiderBoulderOnPath() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spider_white_box_test2", "simple");
        controller.tick(Direction.UP);
        DungeonResponse current_dungeon = controller.tick(Direction.UP);
        Optional<EntityResponse> tmp = current_dungeon.getEntities().stream().filter(entity -> entity.getType().equals("spider")).findFirst();
        EntityResponse spider = tmp.get();
        assertEquals(spider.getPosition(), new Position(2,2));
    }

    @Test
    public void testTwoBoulderStuckSpider() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spider_white_box_test3", "simple");
        controller.tick(Direction.DOWN);
        DungeonResponse current_dungeon = controller.tick(Direction.UP);
        Optional<EntityResponse> tmp = current_dungeon.getEntities().stream().filter(entity -> entity.getType().equals("spider")).findFirst();
        EntityResponse spider = tmp.get();
        assertEquals(spider.getPosition(), new Position(3,2));
    }
}
