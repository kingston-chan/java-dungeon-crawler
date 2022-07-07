package dungeonmania.entities.staticobject.wall;

import dungeonmania.entities.staticobject.StaticObject;

public class Wall extends StaticObject {
    @Override
    public boolean isInteractable() {
        return false;
    }
}
