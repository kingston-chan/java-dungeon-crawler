package dungeonmania.entities.visitor;

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

    default boolean visit(Door door) {
        return true;
    }

    default boolean visit(Exit exit) {
        return true;
    }

    default boolean visit(ZombieToast zombieToast) {
        return true;
    }

    default boolean visit(Spider spider) {
        return true;
    }

    default boolean visit(Mercenary mercenary) {
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
