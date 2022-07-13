package dungeonmania.entities.staticobject.wall;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
<<<<<<< HEAD

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.actor.nonplayableactor.Spider;
=======
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
>>>>>>> a27c47dd9425e0398f4397a78ef321e5db8fd525

public class Wall extends StaticObject {

    @Override
    public boolean canAccept(Player player) {
        return false;
    }

    @Override
    public boolean canAccept(NonPlayableActor enemy) {
        if (enemy instanceof Spider) {
            return true;
        }
        return false;
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
