package dungeonmania.entities.staticobject.door;

import dungeonmania.entities.staticobject.StaticObject;

public class Door extends StaticObject {
    private int keyNum;
    
    @Override
    public boolean isInteractable() {
        return false;
    }

    public void door(int key) {
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
