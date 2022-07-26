package dungeonmania.entities.visitor;

import dungeonmania.entities.actor.nonplayableactor.Hydra;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Bomb;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.entities.staticobject.exit.Exit;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.entities.staticobject.zombietoastspawner.ZombieToastSpawner;

public interface Visit {

    default void visit(Player player) {
    }

    default void visit(Item item) {
    }

    default void visit(Portal portal) {
    }

    default void visit(Boulder boulder) {
    }

    default void visit(ZombieToast zombieToast) {
    }

    default void visit(Spider spider) {
    }

    default void visit(Hydra hydra1) {
    }
    
    default void visit(Mercenary mercenary) {
    }

    default void visit(FloorSwitch fswitch) {
    }

    default void visit(Exit exit) {
    }
}
