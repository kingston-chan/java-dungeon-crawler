package dungeonmania.visiting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.StyledEditorKit.BoldAction;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.assertListAreEqualIgnoringOrder;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.DungeonResponse;


public class MercenaryInteractionTests {
  @Test 
  public void mercenaryVisitsPlayerTest() {
    DungeonManiaController dmc = new DungeonManiaController();

  }

  @Test 
  public void mercenaryVisitsPortalTest() {
    DungeonManiaController controller = new DungeonManiaController();
    controller.newGame("d_mercenaryPortalVisit", "c_Visits");
    Dungeon currentDungeon = controller.getDungeon();
    NonPlayableActor mercenary =  currentDungeon.getNonPlayableActors().get(0);
    List<Boolean> interactionResults = currentDungeon.getDungeonObjects().stream().filter(dungeonObject -> dungeonObject instanceof Portal).map(p -> mercenary.canVisitPortal((Portal) p))
      .collect(Collectors.toList());

    assertListAreEqualIgnoringOrder(Arrays.asList(false,true,true),interactionResults);

    
    DungeonResponse dmc = controller.tick(Direction.RIGHT);
    assertEquals(new Position(3, 6), getEntities(dmc, "mercenary").get(0).getPosition());
  }

  @Test
  public void mercenaryVisitsWallTest() {
    DungeonManiaController controller = new DungeonManiaController();
    controller.newGame("d_mercenaryWallVisit", "c_Visits");
    Dungeon currentDungeon = controller.getDungeon();
    assertFalse(currentDungeon.getNonPlayableActors().get(0).canVisitWall());
    DungeonResponse dmc = controller.getDungeonResponseModel();

    assertEquals(new Position(2, 2), getEntities(dmc, "mercenary").get(0).getPosition());
    dmc = controller.tick(Direction.RIGHT);
    assertEquals(new Position(2, 2), getEntities(dmc, "mercenary").get(0).getPosition());

  }

}
