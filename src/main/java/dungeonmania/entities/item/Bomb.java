package dungeonmania.entities.item;

import dungeonmania.entities.actor.player.Player;

public class Bomb extends Item{

    @Override
    public boolean playerUse(Player player) {
        // generate a new "static bomb" in that player's position
        // check static bomb's adjacent at the same time(method should be provided in static bomb class)
        return true;
    }

    @Override
    public boolean playerEquip(Player player) {
        return false;
    }
}
