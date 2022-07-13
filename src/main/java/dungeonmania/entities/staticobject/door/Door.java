package dungeonmania.entities.staticobject.door;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;

public class Door extends StaticObject {
    private int keyNum;
    private boolean isOpened = false;
    
    @Override
    public boolean isInteractable() {
        return false;
    }

    public void door(int key) {
        this.keyNum = key;
    }

    public boolean doAccept(Player player) {
        if (this.isOpened) {
            return true;
        }
        // if not opened do they have a key.
        return false;
    }

    public boolean doAccept(NonPlayableActor nonplayableactor) {
        if (nonplayableactor instanceof Spider) {
            return true;
        }

        if (this.isOpened) {
            return true;
        }
        return false;
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

