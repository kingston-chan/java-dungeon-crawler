package dungeonmania.entities.staticobject.wall;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.actor.nonplayableactor.Spider;

public class Wall extends StaticObject {
    @Override
    public boolean isInteractable() {
        return false;
    }

    public boolean canAccept(Player player) {
        return false;
    }

    public boolean canAccept(NonPlayableActor enemy) {
        if (enemy instanceof Spider) {
            return true;
        }
        return false;
    }

    public boolean canAccept(Boulder boulder) {
        return false;
    }
}
