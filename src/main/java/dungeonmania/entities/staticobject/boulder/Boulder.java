package dungeonmania.entities.staticobject.boulder;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;


public class Boulder extends StaticObject {
    private FloorSwitch switchActivated = null;

    public FloorSwitch getSwitchActivated() {
        return this.switchActivated;
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

    @Override
    public boolean isInteractable() {
        return false;
    }
}
