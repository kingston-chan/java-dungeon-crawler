package dungeonmania.entities.item;

import dungeonmania.entities.actor.player.Player;

public class LogicBomb extends Bomb {
    // logic behaviour

    @Override
    public boolean playerUse(Player player) {
        // check if logic behaviour rule returns true, if true explode
        // set new logic static bomb
        return true;
    }
}
