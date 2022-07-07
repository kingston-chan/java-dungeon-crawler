package dungeonmania.entities.item.collectables;

import dungeonmania.entities.item.Item;

public class Treasure extends Item {
    @Override
    public boolean isInteractable() {
        return false;
    }
}
