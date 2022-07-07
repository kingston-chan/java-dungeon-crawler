package dungeonmania.entities.staticobject.exit;

import dungeonmania.entities.staticobject.StaticObject;

public class Exit extends StaticObject {
    @Override
    public boolean isInteractable() {
        return false;
    }
}
