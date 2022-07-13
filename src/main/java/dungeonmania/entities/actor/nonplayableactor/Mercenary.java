package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.MercenaryState;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.portal.Portal;

public class Mercenary extends NonPlayableActor {

    MercenaryState enemyState;
    MercenaryState allyState;
    MercenaryState currentState;

    public void doAccept(Player player) {
        player.visit(this);
    }

    public void visit(Portal portal) {
        
    }

    public void setMercenaryState(MercenaryState mercenaryState) {
        this.currentState = mercenaryState;
    }

    public MercenaryState getAllyState() {
        return this.allyState;
    }

    public MercenaryState getEnemyState() {
        return this.enemyState;
    }

    @Override
    public void update(MovementBehaviour movementBehaviour) {
        this.setCurrentMovement(movementBehaviour);
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public boolean canVisitWall() {
        return false;
    }


}
