package dungeonmania.entities.staticobject.door;

import dungeonmania.entities.staticobject.StaticObject;

public class Door extends StaticObject {
    @Override
    public boolean isInteractable() {
        return false;
    }
}
