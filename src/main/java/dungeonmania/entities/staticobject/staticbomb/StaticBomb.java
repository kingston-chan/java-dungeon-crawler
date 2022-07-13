package dungeonmania.entities.staticobject.staticbomb;

import dungeonmania.entities.SwitchObserver;
import dungeonmania.entities.staticobject.StaticObject;

public class StaticBomb extends StaticObject implements SwitchObserver{

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void update(int state) {
        // call the method in util.bombhelper
    }
}
