package dungeonmania;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Position;

import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.getPlayer;


public class persistence {

  @Test
  public void errorTests() {
    DungeonManiaController dmc = new DungeonManiaController();
    assertThrows(IllegalArgumentException.class, () ->  dmc.loadGame("doesnt_exist"));
  }

  @Test
  public void positionTests() {
    DungeonManiaController controller = new DungeonManiaController();
    DungeonResponse dmc = controller.loadGame("positionsTest");


    //assertEquals(new Position(2, 1), getPlayer(dmc).get().getPosition());

    List<String> entityNames = Arrays.asList("mercenary", "spider", "assassin", "hydra", "zombie_toasts");

    for (String entity : entityNames) {
      assertEquals(new Position(entityNames.indexOf(entity), 1), getEntities(dmc, entity).get(0).getPosition());
    }
    
  }

}