package dungeonmania.visiting;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static dungeonmania.TestUtils.assertListAreEqualIgnoringOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import static dungeonmania.TestUtils.getEntities;

public class ZombieInteractionTests {
  @Test 
  public void zombieVisitsPlayerTest() {

  }

  @Test 
  public void zombieVisitsWallTest() {
    DungeonManiaController controller = new DungeonManiaController();
    controller.newGame("d_zombieWallVisit", "c_spiderVisit");
    Dungeon currentDungeon = controller.getDungeon();
    NonPlayableActor spider =  currentDungeon.getNonPlayableActors().get(0);
    assertTrue(spider.canVisitWall());

    DungeonResponse dmc = controller.getDungeonResponseModel();
    assertEquals(new Position(2, 2), getEntities(dmc, "spider").get(0).getPosition());
    dmc = controller.tick(Direction.RIGHT);
    assertEquals(new Position(2, 1), getEntities(dmc, "spider").get(0).getPosition());

  }

  @Test 
  public void zombieVisitsPortalTest() {


  }
}
