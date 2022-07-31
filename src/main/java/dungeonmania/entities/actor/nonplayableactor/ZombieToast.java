package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.entities.actor.player.Player;

public class ZombieToast extends SpecialCreature {
    @Override
    public void doAccept(Player player) {
        player.visit(this);
    }

    @Override
    public void visitInvisiblePlayer(Player player) {
    }
}
