package dungeonmania.entities.item.collectables;

import dungeonmania.entities.staticobject.door.Door;

public class Key extends Collectable {
    private int keyNum;

    public Key(int keyNum) {
        this.keyNum = keyNum;
    }

    public boolean canOpenDoor(Door door) {
        return this.keyNum == door.getKeyNum();
    }
}
