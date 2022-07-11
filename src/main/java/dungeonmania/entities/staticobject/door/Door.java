package dungeonmania.entities.staticobject.door;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;

public class Door extends StaticObject {
    private int keyNum;
    
    @Override
    public boolean isInteractable() {
        return false;
    }

    public Door(int key) {
        this.keyNum = key;
    }

    public boolean accept(Player player) {
        //check if keyNum matches a key in player inventory 
        return true;
    }

    public boolean accept(NonPlayableActor enemy) {
        return false;
    }

    public boolean accept(Boulder boulder) {
        return true;
    }

    public int getKeyNum() {
        return this.keyNum;
    }

    
}
