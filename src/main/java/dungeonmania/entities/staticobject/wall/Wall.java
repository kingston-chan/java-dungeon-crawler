package dungeonmania.entities.staticobject.wall;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;

public class Wall extends StaticObject {
    @Override
    public boolean isInteractable() {
        return false;
    }

    public boolean accept(Player player) {
        return false;
    }

    public boolean accept(NonPlayableActor enemy) {
        return false;
    }

    public boolean accept(Boulder boulder) {
        return false;
    }
}
