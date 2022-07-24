package dungeonmania;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.assertListAreEqualIgnoringOrder;


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

    List<String> mobNames = Arrays.asList("player", "mercenary", "spider", "zombie_toast","zombie_toast_spawner");

    for (String entity : mobNames) {
      assertEquals(new Position(mobNames.indexOf(entity), 1), getEntities(dmc, entity).get(0).getPosition());
    }

    List<String> staticNames = Arrays.asList("wall", "exit", "boulder", "switch", "door", "portal", "treasure", "wood", "arrow", "bomb", "sword","invincibility_potion", "invisibility_potion", "sun_stone");

    for (String entity : staticNames) {
      assertEquals(new Position(staticNames.indexOf(entity), 2), getEntities(dmc, entity).get(0).getPosition());
    }
    
  }

  @Test 
  public void savingTest() {
    DungeonManiaController controller = new DungeonManiaController();

    DungeonResponse r = controller.newGame("d_positionsPersistence", "simple");
    DungeonResponse dmc = controller.saveGame("positionsTest");

  }

  @Test 
  public void ListDirectorysTest() {
    DungeonManiaController controller = new DungeonManiaController();

    DungeonResponse r = controller.newGame("d_positionsPersistence", "simple");
    List<String> l = controller.allGames();

    for (String s : l){
      System.out.println(s);
    }
  }

  @Test 
  public void saveMidWayTest() {
    DungeonManiaController controller = new DungeonManiaController();

    DungeonResponse r = controller.newGame("d_midwayPersistence", "simple");
    DungeonResponse dmc = controller.tick(Direction.RIGHT);
    dmc = controller.tick(Direction.RIGHT);
    dmc = controller.tick(Direction.RIGHT);
    controller.saveGame("midwayTest");

  }

  @Test 
  public void loadMidWayTest() {
    DungeonManiaController controller = new DungeonManiaController();
    DungeonResponse dmc = controller.loadGame("midwayTest");
    Dungeon dungeon = controller.getDungeon();
    Player p = dungeon.getPlayer();

    // Player position
    assertEquals(new Position(3, 1), p.getPosition());

    //Player inventory 
    List<String> inventory = p.getInventory().stream().map(Item::getType).collect(Collectors.toList());
    assertListAreEqualIgnoringOrder(Arrays.asList("wood", "treasure"), inventory);

    List<DungeonObject> o = dungeon.getDungeonObjects();

    for (DungeonObject f : o) {
      System.out.println(f.getType());
    }
  }
}

