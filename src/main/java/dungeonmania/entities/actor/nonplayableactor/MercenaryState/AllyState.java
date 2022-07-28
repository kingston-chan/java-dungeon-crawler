package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.behaviours.movement.FollowPlayer;
import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.player.Player;

public class AllyState implements MercenaryState {
    private Mercenary mercenary;
    private MovementBehaviour allyMovement = new FollowPlayer();

    public AllyState(Mercenary mercenary) {
        this.mercenary = mercenary;
    }

    public Mercenary getMercenary() {
        return this.mercenary;
    }

    @Override
    public boolean canInteract() {
        return false;
    }

    @Override
    public boolean isAlly() {
        return true;
    }

    @Override
    public void recruitedBy(Player player) {
    }

    @Override
    public void mindcontrol() {
    }

    @Override
    public int bribeAmount() {
        return 0;
    }

    @Override
    public void visitInvisiblePlayer(Player player) {
    }

    @Override
    public boolean isAssassin() {
        return false;
    }

    private void move() {
        mercenary.setCurrentMovement(allyMovement);
        mercenary.doMove();
    }

    @Override
    public void movePlayerIsNormal() {
        move();
    }

    @Override
    public void movePlayerIsInvincible() {
        move();
    }

    @Override
    public void movePlayerIsInvisible() {
        move();
    }
}
