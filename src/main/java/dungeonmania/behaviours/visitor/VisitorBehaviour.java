package dungeonmania.behaviours.visitor;

import dungeonmania.entities.actor.nonplayableactor.MercenaryEnemy;
import dungeonmania.entities.actor.nonplayableactor.SpiderEnemy;
import dungeonmania.entities.actor.nonplayableactor.ZombieEnemy;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Bomb;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.entities.staticobject.exit.Exit;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.entities.staticobject.wall.Wall;
import dungeonmania.entities.staticobject.zombietoastspawner.ZombieToastSpawner;

public interface VisitorBehaviour {
  default boolean visit(Player player) {
    return true;
  }

  default boolean visit(Item item) {
    return true;
  }

  default boolean visit(Portal portal) {
    return true;
  }

  default boolean visit(Boulder boulder) {
    return true;
  }

  default boolean visit(Wall wall) {
    return true;
  }

  default boolean visit(Door door) {
    return true;
  }

  default boolean visit(Exit exit) {
    return true;
  }

  default boolean visit(ZombieEnemy zombie) {
    return true;
  }

  default boolean visit(SpiderEnemy spider) {
    return true;
  }

  default boolean visit(MercenaryEnemy mercenary) {
    return true;
  }

  default boolean visit(FloorSwitch fswitch) {
    return true;
  }

  default boolean visit(ZombieToastSpawner spawner) {
    return true;
  }

  default boolean visit(Bomb bomb) {
    return true;
  }
}
