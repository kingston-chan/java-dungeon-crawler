package dungeonmania.entities.staticobject.wall;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.actor.nonplayableactor.Spider;

public class Wall extends StaticObject {

    @Override
    public boolean canAccept(Player player) {
        return false;
    }

    @Override
    public boolean canAccept(NonPlayableActor enemy) {
        return enemy.canVisitWall();
    }

    @Override
    public boolean canAccept(Boulder boulder) {
        return false;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
}
