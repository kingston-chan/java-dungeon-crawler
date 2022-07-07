package dungeonmania.entities.item.potion;

import dungeonmania.entities.item.Item;

public abstract class Potion extends Item {
    @Override
    public boolean isInteractable() {
        return false;
    }
}
