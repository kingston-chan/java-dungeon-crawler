package dungeonmania.movementtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SpiderMovementTests {
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
