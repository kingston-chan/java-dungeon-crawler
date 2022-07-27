package dungeonmania.entities.staticobject.lightbulb;

import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.staticbomb.SwitchObserver;

public class Lightbulb extends StaticObject implements SwitchObserver {
    public boolean isActive = false;

    @Override
    public void update() {
        isActive = !isActive;

        if (isActive) {
            setType("light_bulb_on");
        } else {
            setType("light_bulb_off");
        }
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

}
