package dungeonmania.logicalentities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.countEntityOfType;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SwitchDoorTest {
    
    // or door already tested

    @Test
        public void andDoorFails() {
            DungeonManiaController controller = new DungeonManiaController();
            DungeonResponse currentDungeon = controller.newGame("d_andDoor", "simple");
    
            //push a bolder onto a switch
            currentDungeon = controller.tick(Direction.RIGHT);
            currentDungeon = controller.tick(Direction.UP);
    
            EntityResponse player = getPlayer(currentDungeon).get();
            
            //door is not open
            assertEquals(new Position(3, 2), player.getPosition());
    }

    @Test
        public void andDoorpass() {
            DungeonManiaController controller = new DungeonManiaController();
            DungeonResponse currentDungeon = controller.newGame("d_andDoorpass", "simple");
    
            //push a bolder onto a switch
            currentDungeon = controller.tick(Direction.RIGHT);
            currentDungeon = controller.tick(Direction.UP);
    
            EntityResponse player = getPlayer(currentDungeon).get();
            
            //door is near two entities so opens
            assertEquals(new Position(3, 1), player.getPosition());
    }

    @Test
        public void xorDoorfail() {
            DungeonManiaController controller = new DungeonManiaController();
            DungeonResponse currentDungeon = controller.newGame("d_xordoorfail", "simple");
    
            //push a bolder onto a switch
            currentDungeon = controller.tick(Direction.RIGHT);
            currentDungeon = controller.tick(Direction.UP);
    
            EntityResponse player = getPlayer(currentDungeon).get();
            
            //door is near 2 entities
            assertEquals(new Position(3, 2), player.getPosition());
    }

    @Test
    public void xorDoorpass() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_xordoor", "simple");

        //push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.UP);

        EntityResponse player = getPlayer(currentDungeon).get();
        
        //door is near 1 entitie so its open
        assertEquals(new Position(3, 1), player.getPosition());
    }

    @Test
    public void co_andDoorpass() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_co_anddoor", "simple");

        //push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.UP);

        EntityResponse player = getPlayer(currentDungeon).get();
        
        //door is near 2 entities activated at the same time
        assertEquals(new Position(3, 1), player.getPosition());
}



    @Test
    public void co_andDoorfail() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_co_anddoorfail", "simple");

        //push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.LEFT);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.UP);
        currentDungeon = controller.tick(Direction.RIGHT);

        EntityResponse player = getPlayer(currentDungeon).get();
        
        //entities not actiavted at the same times
        assertEquals(new Position(3, 3), player.getPosition());
    }
}
