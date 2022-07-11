package dungeonmania.entities.staticobject.wall;

import dungeonmania.entities.staticobject.StaticObject;

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
