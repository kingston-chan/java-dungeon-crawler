package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.battle.Battle;

public class Hydra extends SpecialCreature {
    @Override
    public void doAccept(Player player) {
        player.visit(this);
    }

    @Override
    public void visit(Player player) {
        Battle battle = new Battle(this.getType(), this.getHealthPoints(), player.getHealthPoints());
        battle.simulateHydraBattle(player, this);
    }

    @Override
    public void visitInvisiblePlayer(Player player) {
    }
}
