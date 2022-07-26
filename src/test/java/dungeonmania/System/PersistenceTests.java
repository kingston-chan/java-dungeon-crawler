package dungeonmania.System;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import static dungeonmania.TestUtils.assertListAreEqualIgnoringOrder;
import static dungeonmania.TestUtils.getGoals;
import static dungeonmania.TestUtils.getPlayer;


public class PersistenceTests {

  @Test
  public void errorTests() {
    DungeonManiaController dmc = new DungeonManiaController();
    assertThrows(IllegalArgumentException.class, () ->  dmc.loadGame("doesnt_exist"));
  }

  @Test 
  public void savingTest() {
    DungeonManiaController controller = new DungeonManiaController();

    controller.newGame("d_positionsPersistence", "c_persistence");
    assertTrue(controller.saveGame("positionsTest") instanceof DungeonResponse);

  }

  @Test
  public void positionTests() {
    DungeonManiaController controller = new DungeonManiaController();
    DungeonResponse dmc = controller.loadGame("positionsTest");

    List<String> mobNames = Arrays.asList("player", "mercenary", "spider", "zombie_toast","zombie_toast_spawner", "assassin", "hydra");

    for (String entity : mobNames) {
      assertEquals(new Position(mobNames.indexOf(entity), 1), getEntities(dmc, entity).get(0).getPosition());
    }

    List<String> staticNames = Arrays.asList("wall", "exit", "boulder", "switch", "door", "portal", "treasure", "wood", "arrow", "bomb", "sword","invincibility_potion", "invisibility_potion", "sun_stone", "swamp_tile");

    for (String entity : staticNames) {
      assertEquals(new Position(staticNames.indexOf(entity), 2), getEntities(dmc, entity).get(0).getPosition());
    }
    
  }

  

  @Test 
  public void ListDirectorysTest() {
    DungeonManiaController controller = new DungeonManiaController();

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

    // Goal 
    assertEquals(":exit", getGoals(dmc));

    List<DungeonObject> o = dungeon.getDungeonObjects();

    for (DungeonObject f : o) {
      System.out.println(f.getType());
    }
  }

  @Test 
  public void switchDungeonTest() {
    DungeonManiaController controller = new DungeonManiaController();
    controller.newGame("d_positionsPersistence", "c_persistence");
    Dungeon prevDungeon = controller.getDungeon();
    controller.loadGame("midwayTest");
    Dungeon newDungeon = controller.getDungeon();
    assertNotEquals(prevDungeon, newDungeon);

  }

  @Test 
  public void swampTilePersistenceTest() {
    DungeonManiaController controller = new DungeonManiaController();
    controller.newGame("d_swampPersistence", "c_persistence");
    controller.tick((Direction.DOWN));
    controller.saveGame("onSwampTile");

    DungeonManiaController controller2 = new DungeonManiaController();
    DungeonResponse dmc = controller2.loadGame("onSwampTile");

    assertEquals(new Position(1, 1), getEntities(dmc, "spider").get(0).getPosition());
    assertEquals(new Position(3, 2), getEntities(dmc, "mercenary").get(0).getPosition());
    dmc = controller.tick((Direction.DOWN));
    assertEquals(new Position(2, 1), getEntities(dmc, "spider").get(0).getPosition());
    assertEquals(new Position(3, 2), getEntities(dmc, "mercenary").get(0).getPosition());
    dmc = controller.tick((Direction.DOWN));
    assertEquals(new Position(2, 2), getEntities(dmc, "spider").get(0).getPosition()); 
    assertEquals(new Position(3, 2), getEntities(dmc, "mercenary").get(0).getPosition()); 
    dmc = controller.tick((Direction.DOWN));
    assertEquals(new Position(2, 3), getEntities(dmc, "spider").get(0).getPosition()); 
    assertEquals(new Position(3, 3), getEntities(dmc, "mercenary").get(0).getPosition());   
  }

  @Test
  public void mercancyRemainsAllyPersistenceTest() {
    DungeonManiaController controller = new DungeonManiaController();
    controller.newGame("d_mercenaryInteract",
            "c_battleTests_basicMercenaryMercenaryDies");

    DungeonResponse dres = controller.tick(Direction.RIGHT);

    EntityResponse merc = getEntities(dres, "mercenary").get(0);
    Position playerPrevPos = getPlayer(dres).get().getPosition();

    assertDoesNotThrow(() -> controller.interact(merc.getId()));

    controller.saveGame("bribedMercenary");

    DungeonManiaController controller2 = new DungeonManiaController();
    controller2.loadGame("bribedMercenary");
    DungeonResponse dmc = controller2.tick(Direction.RIGHT);
    EntityResponse ally = getEntities(dmc, "mercenary").get(0);
    assertEquals(playerPrevPos, ally.getPosition());
    assertFalse(ally.isInteractable());
  }
}

