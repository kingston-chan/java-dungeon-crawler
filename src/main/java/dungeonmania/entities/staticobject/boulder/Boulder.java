package dungeonmania.entities.staticobject.boulder;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.exit.Exit;
import dungeonmania.entities.staticobject.floorswitch.ActivatedState;
import dungeonmania.entities.staticobject.floorswitch.DeactivatedState;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.portal.Portal;

import java.util.ArrayList;
import java.util.List;


import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;

public class Boulder extends StaticObject {

    public boolean canAccept(Player player) {
        return true;
    }

    public boolean canAccept(NonPlayableActor enemy) {
        return false;
    }

    public boolean canAccept(Boulder boulder) {
        return false;
    }

    public void doAccept(Player player) {
        player.visit(this);
    }

    public void visit(FloorSwitch floorSwitch) {
        this.setPosition(floorSwitch.getPosition()); 
        floorSwitch.doActivate();
    }

    public void visit(Exit exit) {
        this.setPosition(exit.getPosition()); 
    }

    public void visit(Item item) {
        this.setPosition(item.getPosition()); 
    }

    public void visit(Portal portal) {
        this.setPosition(portal.getPosition()); 
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
}
