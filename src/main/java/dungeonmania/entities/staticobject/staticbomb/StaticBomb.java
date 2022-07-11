package dungeonmania.entities.staticobject.staticbomb;

import dungeonmania.entities.staticobject.StaticObject;

public class StaticBomb extends StaticObject {

    @Override
    public boolean isInteractable() {
        return false;
    }

    public void update(int state) {
        // do something switch related.
    }
}
