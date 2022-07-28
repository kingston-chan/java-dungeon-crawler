package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import java.util.Random;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.FollowPlayer;
import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.util.BoxRadius;

public class AssassinState implements MercenaryState {

    private Mercenary assassin;
    private int reconRadius;

    public AssassinState(Mercenary assassin, int reconRadius) {
        this.assassin = assassin;
        this.reconRadius = reconRadius;
    }

    private boolean checkIfPlayerInRadius(Player player) {
        return BoxRadius.getBoxRadiusPositions(reconRadius, assassin.getPosition()).contains(player.getPosition());
    }

    @Override
    public boolean canInteract() {
        return true;
    }

    @Override
    public void updateMovement(MovementBehaviour movementBehaviour) {
        Player player = DungeonManiaController.getDungeon().getPlayer();
        if (player.isInvisible() && checkIfPlayerInRadius(player)) {
            assassin.setCurrentMovement(assassin.getDefaultMovement());
        } else {
            assassin.setCurrentMovement(movementBehaviour);
        }
    }

    @Override
    public boolean isAlly() {
        return false;
    }

    @Override
    public void recruitedBy(Player player) {
        long seed = (System.currentTimeMillis() / 100) * 100;
        Random rng = new Random(seed);
        double fail_rate = DungeonManiaController.getDungeon().getDoubleConfig("assassin_bribe_fail_rate");
        if (rng.nextDouble() >= fail_rate) {
            player.addAlly();
            assassin.setMercenaryState(assassin.getAllyState());
            MovementBehaviour allyMovement = new FollowPlayer();
            assassin.setCurrentMovement(allyMovement);
        }
    }

    @Override
    public void mindcontrol() {
<<<<<<< HEAD

=======
        assassin.setMercenaryState(assassin.getMindcontrolState());
        MovementBehaviour mindcontrolMovement = new FollowPlayer();
        assassin.setCurrentMovement(mindcontrolMovement);
>>>>>>> e5ce0254cca0a742955f4177de29731bb2367a9a
    }

    @Override
    public int bribeAmount() {
        return DungeonManiaController.getDungeon().getIntConfig("assassin_bribe_amount");
    }

    @Override
    public void visitInvisiblePlayer(Player player) {
        assassin.visit(player);
    }

    @Override
    public boolean isAssassin() {
        return true;
    }
}
