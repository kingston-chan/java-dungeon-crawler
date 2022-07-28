package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import java.util.Random;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.util.BoxRadius;

public class AssassinState extends EnemyState {
    private int reconRadius;

    public AssassinState(Mercenary assassin, int reconRadius) {
        super(assassin);
        this.reconRadius = reconRadius;
    }

    private boolean checkIfPlayerInRadius(Player player) {
        return BoxRadius.getBoxRadiusPositions(reconRadius, getEnemy().getPosition()).contains(player.getPosition());
    }

    @Override
    public void recruitedBy(Player player) {
        Random rng = new Random();
        double fail_rate = DungeonManiaController.getDungeon().getDoubleConfig("assassin_bribe_fail_rate");
        if (rng.nextDouble() >= fail_rate) {
            super.recruitedBy(player);
        }
    }

    @Override
    public int bribeAmount() {
        return DungeonManiaController.getDungeon().getIntConfig("assassin_bribe_amount");
    }

    @Override
    public void visitInvisiblePlayer(Player player) {
        getEnemy().visit(player);
    }

    @Override
    public boolean isAssassin() {
        return true;
    }

    @Override
    public void movePlayerIsInvisible() {
        Player player = DungeonManiaController.getDungeon().getPlayer();
        if (checkIfPlayerInRadius(player)) {
            super.movePlayerIsNormal();
        } else {
            super.movePlayerIsInvisible();
        }
    }
}
