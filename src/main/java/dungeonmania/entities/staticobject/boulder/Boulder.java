package dungeonmania.entities.staticobject.boulder;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.floorswitch.ActivatedState;
import dungeonmania.entities.staticobject.floorswitch.DeactivatedState;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;

import java.util.ArrayList;
import java.util.List;


import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;

public class Boulder extends StaticObject {
    private FloorSwitch switchActivated = null;

    public FloorSwitch getSwitchActivated() {
        return this.switchActivated;
    }

    public boolean accept(Player player) {
        return true;
    }

    public boolean accept(NonPlayableActor enemy) {
        if (enemy instanceof Spider) {
            return false;
        }
        return true;
    }

    public boolean accept(Boulder boulder) {
        return false;
    }

    public boolean visit(FloorSwitch floorSwitch) {
        /*Dungeon dungeon = DungeonManiaController.getDungeon();
        dungeon.getDungeonObjects().stream().filter(obj -> obj.getPosition() == floorSwitch.getPosition()).allMatch(dungeonobj -> dungeonobj.accept(this)); */
        return floorSwitch.doActivate();
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
}
