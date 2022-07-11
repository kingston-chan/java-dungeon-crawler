package dungeonmania.entities.staticobject.boulder;

import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;

public class Boulder extends StaticObject {
    @Override
    public boolean isInteractable() {
        return false;
    }

    public boolean accept(Player player) {
        return true;
    }

    public boolean accept(NonPlayableActor enemy) {
        return false;
    }

    public boolean accept(Boulder boulder) {
        return false;
    }

    public boolean visit(FloorSwitch floorSwitch) {
        return true;
    }
}
