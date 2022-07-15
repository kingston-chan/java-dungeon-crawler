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

public class SpiderWhiteBoxTest {
    @Test
    public void testSpiderBoulderOnUpOfSpiderSpawn(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spider_white_box_test1", "simple");
        controller.tick(Direction.UP);
        DungeonResponse current_dungeon = controller.tick(Direction.UP);
        Optional<EntityResponse> tmp = current_dungeon.getEntities().stream().filter(entity -> entity.getType().equals("spider")).findFirst();
        EntityResponse spider = tmp.get();
        System.out.println(spider.getPosition());
        assertEquals(spider.getPosition(), new Position(3,3));
    }

    @Test
    public void testSpiderBoulderOnPath(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spider_white_box_test2", "simple");
        controller.tick(Direction.UP);
        DungeonResponse current_dungeon = controller.tick(Direction.UP);
        Optional<EntityResponse> tmp = current_dungeon.getEntities().stream().filter(entity -> entity.getType().equals("spider")).findFirst();
        EntityResponse spider = tmp.get();
        System.out.println(spider.getPosition());
        assertEquals(spider.getPosition(), new Position(2,2));
    }
}
