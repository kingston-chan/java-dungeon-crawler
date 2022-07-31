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
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.states.InvinicibleState;
import dungeonmania.entities.actor.player.states.InvisibleState;
import dungeonmania.entities.actor.player.states.NormalState;
import dungeonmania.entities.item.Item;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.assertListAreEqualIgnoringOrder;
import static dungeonmania.TestUtils.getGoals;
import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.getInventory;

public class PersistenceTests {

  @Test
  public void errorTests() {
    DungeonManiaController dmc = new DungeonManiaController();
    assertThrows(IllegalArgumentException.class, () -> dmc.loadGame("doesnt_exist"));
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

    List<String> mobNames = Arrays.asList("player", "mercenary", "spider", "zombie_toast", "zombie_toast_spawner",
        "assassin", "hydra");

    for (String entity : mobNames) {
      assertEquals(new Position(mobNames.indexOf(entity), 1), getEntities(dmc, entity).get(0).getPosition());
    }

    List<String> staticNames = Arrays.asList("wall", "exit", "boulder", "switch", "door", "portal", "treasure", "wood",
        "arrow", "bomb", "sword", "invincibility_potion", "invisibility_potion", "sun_stone", "swamp_tile");

    for (String entity : staticNames) {
      assertEquals(new Position(staticNames.indexOf(entity), 2), getEntities(dmc, entity).get(0).getPosition());
    }
  }

  @Test
  public void ListDirectorysTest() {
    DungeonManiaController controller = new DungeonManiaController();

    List<String> results = controller.allGames();
    List<String> expectedfileNames = Arrays.asList("midwayTest", "invisibleState", "positionsTest", "test_saving",
        "bribedMercenary", "invincibleState", "onSwampTile", "merceneryUnderMindControl", "assassinUnderMindControl");

    for (String fileName : expectedfileNames) {
      assertTrue(results.contains(fileName));
    }
  }

  @Test
  public void savePlayerTest() {
    DungeonManiaController controller = new DungeonManiaController();
    controller.newGame("d_midwayPersistence", "simple");
    controller.tick(Direction.RIGHT);
    controller.tick(Direction.RIGHT);
    controller.tick(Direction.RIGHT);
    controller.tick(Direction.RIGHT);
    controller.tick(Direction.RIGHT);
    controller.tick(Direction.RIGHT);
    assertDoesNotThrow(() -> controller.build("bow"));
    assertTrue(controller.saveGame("midwayTest") instanceof DungeonResponse);

  }

  @Test
  public void loadPlayerTest() {
    DungeonManiaController controller = new DungeonManiaController();
    DungeonResponse dmc = controller.loadGame("midwayTest");
    Dungeon dungeon = DungeonManiaController.getDungeon();
    Player p = dungeon.getPlayer();

    // Player position
    assertEquals(new Position(6, 1), p.getPosition());

    // Player inventory
    List<String> inventory = p.getInventory().stream().map(Item::getType).collect(Collectors.toList());
    assertListAreEqualIgnoringOrder(Arrays.asList("bow", "treasure", "arrow"), inventory);

    // Goal
    assertEquals(":exit", getGoals(dmc));

    // State
    assertTrue(DungeonManiaController.getDungeon().getPlayer().getCurrentPlayerState() instanceof NormalState);
  }

  @Test
  public void switchDungeonTest() {
    DungeonManiaController controller = new DungeonManiaController();
    DungeonResponse dmc = controller.newGame("d_positionsPersistence", "c_persistence");
    String dungeonId1 = dmc.getDungeonId();
    Dungeon prevDungeon = DungeonManiaController.getDungeon();
    dmc = controller.loadGame("midwayTest");
    String dungeonId2 = dmc.getDungeonId();
    Dungeon newDungeon = DungeonManiaController.getDungeon();
    assertNotEquals(prevDungeon, newDungeon);
    assertNotEquals(dungeonId1, dungeonId2);

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
  public void mercancyAllyPersistsTest() {
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

  @Test
  public void invincibilityPotionPersistsTest() {
    DungeonManiaController controller = new DungeonManiaController();
    controller.newGame("d_potionPersistence", "c_persistence");
    DungeonResponse dmc = controller.tick(Direction.RIGHT);
    ItemResponse potion = getInventory(dmc, "invincibility_potion").get(0);
    assertDoesNotThrow(() -> controller.tick(potion.getId()));

    assertTrue(DungeonManiaController.getDungeon().getPlayer().getCurrentPlayerState() instanceof InvinicibleState);
    dmc = controller.tick(Direction.RIGHT);
    assertTrue(DungeonManiaController.getDungeon().getPlayer().getCurrentPlayerState() instanceof InvinicibleState);

    controller.saveGame("invincibleState");

    DungeonManiaController controller2 = new DungeonManiaController();
    controller2.loadGame("invincibleState");
    assertTrue(DungeonManiaController.getDungeon().getPlayer().getCurrentPlayerState() instanceof InvinicibleState);
    controller.tick(Direction.RIGHT);
    assertTrue(DungeonManiaController.getDungeon().getPlayer().getCurrentPlayerState() instanceof InvinicibleState);
    controller.tick(Direction.RIGHT);
    assertTrue(DungeonManiaController.getDungeon().getPlayer().getCurrentPlayerState() instanceof NormalState);
  }

  @Test
  public void invisiblePotionPersistsTest() {
    DungeonManiaController controller = new DungeonManiaController();
    controller.newGame("d_potionPersistence", "c_persistence");
    DungeonResponse dmc = controller.tick(Direction.DOWN);
    ItemResponse potion = getInventory(dmc, "invisibility_potion").get(0);
    assertDoesNotThrow(() -> controller.tick(potion.getId()));

    assertTrue(DungeonManiaController.getDungeon().getPlayer().getCurrentPlayerState() instanceof InvisibleState);
    dmc = controller.tick(Direction.DOWN);
    assertTrue(DungeonManiaController.getDungeon().getPlayer().getCurrentPlayerState() instanceof InvisibleState);

    controller.saveGame("invisibleState");

    DungeonManiaController controller2 = new DungeonManiaController();
    controller2.loadGame("invisibleState");
    assertTrue(DungeonManiaController.getDungeon().getPlayer().getCurrentPlayerState() instanceof InvisibleState);
    controller.tick(Direction.DOWN);
    assertTrue(DungeonManiaController.getDungeon().getPlayer().getCurrentPlayerState() instanceof NormalState);
  }

  @Test
  public void mercenaryMindControlPersistsTest() {
    DungeonManiaController controller = new DungeonManiaController();
    DungeonResponse resp = controller.newGame("d_mindcontrol", "c_scptre_simple");
    String mercenary_id = getEntities(resp, "mercenary").get(0).getId();
    controller.tick(Direction.RIGHT);
    controller.tick(Direction.RIGHT);
    resp = controller.tick(Direction.RIGHT);
    assertDoesNotThrow(() -> controller.build("sceptre"));
    assertDoesNotThrow(() -> controller.interact(mercenary_id));
    resp = controller.tick(Direction.RIGHT);
    // merceanry still alive beacsue mind control is in an ally state
    assertTrue(resp.getEntities().stream().anyMatch(entity -> entity.getType().equals("mercenary")));

    controller.saveGame("merceneryUnderMindControl");

    DungeonManiaController controller2 = new DungeonManiaController();
    DungeonResponse dmc = controller2.loadGame("merceneryUnderMindControl");

    assertTrue(dmc.getEntities().stream().anyMatch(entity -> entity.getType().equals("mercenary")));
    dmc = controller2.tick(Direction.RIGHT);
    assertTrue(dmc.getEntities().stream().anyMatch(entity -> entity.getType().equals("mercenary")));
    controller2.tick(Direction.RIGHT);

    dmc = controller2.tick(Direction.LEFT);
    assertFalse(dmc.getEntities().stream().anyMatch(entity -> entity.getType().equals("mercenary")));

  }

  @Test
  public void assassinMindControlPersistsTest() {
    DungeonManiaController controller = new DungeonManiaController();
    DungeonResponse res = controller.newGame("d_mindcontrolAssassin", "c_assassinRecon");
    String assassinId = getEntities(res, "assassin").get(0).getId();
    controller.tick(Direction.RIGHT);
    controller.tick(Direction.RIGHT);
    controller.tick(Direction.RIGHT);
    // should have all the materials to build a sceptre
    assertDoesNotThrow(() -> controller.build("sceptre"));
    assertDoesNotThrow(() -> controller.interact(assassinId));
    res = controller.tick(Direction.RIGHT);
    // assassin is ally therefore should not be interactable
    assertFalse(getEntities(res, "assassin").get(0).isInteractable());
    res = controller.tick(Direction.RIGHT);
    controller.saveGame("assassinUnderMindControl");

    DungeonManiaController controller2 = new DungeonManiaController();
    DungeonResponse dmc = controller2.loadGame("assassinUnderMindControl");
    assertFalse(getEntities(dmc, "assassin").get(0).isInteractable());
    dmc = controller.tick(Direction.RIGHT);
    // should be interactable i.e. no longer ally/under mind control since duration
    // is 3
    assertTrue(getEntities(dmc, "assassin").get(0).isInteractable());
    dmc = controller.tick(Direction.RIGHT);
    assertTrue(getEntities(dmc, "assassin").get(0).isInteractable());
  }

  @Test
  public void battlePersistsTest() {
    DungeonManiaController controller = new DungeonManiaController();
    controller.newGame("d_playerBattlesZombie", "c_playerDefeatsZombie");
    DungeonResponse resp = controller.tick(Direction.RIGHT);
    assertFalse(resp.getEntities().stream().anyMatch(entity -> entity.getType().equals("zombie_toast")));
    controller.saveGame("battledZombie");

    DungeonManiaController controller2 = new DungeonManiaController();
    DungeonResponse dmc = controller2.loadGame("battledZombie");
    assertEquals(1, dmc.getBattles().size());
    assertEquals("zombie_toast", dmc.getBattles().get(0).getEnemy());

  }
}
