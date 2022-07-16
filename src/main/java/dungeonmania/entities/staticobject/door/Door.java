package dungeonmania.entities.staticobject.door;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Key;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;

public class Door extends StaticObject {
    private int keyNum;
    private boolean isOpened = false;

    public Door(int key) {
        this.keyNum = key;
    }

    public int getKeyNum() {
        return this.keyNum;
    }

    @Override
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
            this.isOpened = true;
        }

        return this.isOpened;
    }

    @Override
    public boolean canAccept(NonPlayableActor nonplayableactor) {
        return this.isOpened;
    }

    @Override
    public boolean canAccept(Boulder boulder) {
        return this.isOpened;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
}
