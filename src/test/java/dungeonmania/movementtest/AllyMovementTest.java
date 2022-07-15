package dungeonmania.movementtest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class AllyMovementTest {
    
    @Test
    public void testAllyFollowPlayer() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_movementtest_AllyMovementTest", "simple");

        // player picks up treasure        
        DungeonResponse dr = controller.tick(Direction.RIGHT); 
        
        assertTrue(dr.getInventory().size() == 1);
         
        // player bribes merc
        EntityResponse merc = dr.getEntities().stream().filter(e -> e.getType().equals("mercenary")).findFirst().get(); 
        assertDoesNotThrow(() -> controller.interact(merc.getId()));
        
        // player moves right, ally should be in player prev position
        dr = controller.tick(Direction.RIGHT);
        
        Optional<EntityResponse> tmp = dr.getEntities().stream().filter(entity -> entity.getType().equals("mercenary")).findFirst();
        EntityResponse ally = tmp.get();
        assertEquals(ally.getPosition(), new Position(2, 1));
        
        // player moves right, ally should be in player prev position
        dr = controller.tick(Direction.RIGHT);

        tmp = dr.getEntities().stream().filter(entity -> entity.getType().equals("mercenary")).findFirst();
        ally = tmp.get();
        assertEquals(ally.getPosition(), new Position(3, 1));

    }

    @Test
    public void testAllyPlayerItemUse() {
        // DungeonManiaController controller = new DungeonManiaController();
        // controller.newGame("d_movementtest_AllyItemUse", "simple");

        // // player picks up treasure
        // DungeonResponse dr = controller.tick(Direction.RIGHT); 
        // assertTrue(dr.getInventory().size() == 1);

        // // player picks up invis potion
        // dr = controller.tick(Direction.RIGHT); 
        // assertTrue(dr.getInventory().size() == 2);
        
        // // player bribes merc
        // EntityResponse merc = dr.getEntities().stream().filter(e -> e.getType().equals("mercenary")).findFirst().get(); 
        // dr = assertDoesNotThrow(() -> controller.interact(merc.getId()));
        // assertTrue(dr.getInventory().size() == 1);

        // // player drinks invis potion
        // ItemResponse item1 = dr.getInventory().get(0);
        // dr = assertDoesNotThrow(() -> controller.tick(item1.getId()));
        // assertTrue(dr.getInventory().size() == 0);

        // // ally should move to the same position as the player
        // Optional<EntityResponse> tmp = dr.getEntities().stream().filter(entity -> entity.getType().equals("mercenary")).findFirst();
        // EntityResponse ally = tmp.get();
        // assertEquals(ally.getPosition(), new Position(3, 1));

        // // player moves down, ally should stay still, in players previous location
        // dr = controller.tick(Direction.UP);
        // tmp = dr.getEntities().stream().filter(entity -> entity.getType().equals("mercenary")).findFirst();
        // ally = tmp.get();
        // assertEquals(ally.getPosition(), new Position(3, 1));
    }
}
