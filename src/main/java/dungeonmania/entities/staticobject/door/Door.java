package dungeonmania.entities.staticobject.door;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.collectables.Key;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;

public class Door extends StaticObject {
    private int keyNum;
    private boolean isOpened = false;
    
    @Override
    public boolean isInteractable() {
        return false;
    }

    public Door(int key) {
        this.keyNum = key;
    }

    public boolean canAccept(Player player) {
        if (this.isOpened) {
            return true;
        }

        Key key = player.getKey();
        if (key == null) {
            return false;
        }

        if (key.canOpenDoor(this)) {
            player.removeFromInventory(key);
            this.isOpened = key.canOpenDoor(this);
        }
        
        return this.isOpened;
    }

    public boolean canAccept(NonPlayableActor nonplayableactor) {
        return this.isOpened;
    }
    
    public boolean accept(Boulder boulder) {
        return this.isOpened;
    }

    public int getKeyNum() {
        return this.keyNum;
    }
}

