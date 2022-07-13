package dungeonmania.entities.item;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.collectables.Collectable;
import dungeonmania.entities.staticobject.door.Door;

public class Key extends Item {
    private int keyNum;

    public Key(int keyNum) {
        this.keyNum = keyNum;
    }

    public boolean canOpenDoor(Door door) {
        return this.keyNum == door.getKeyNum();
    }

    @Override
    public void doAccept(Player player) {
        player.tryPickUpKey(this);
    }

    @Override
    public boolean playerUse(Player player) {
        return false;
    }
}
