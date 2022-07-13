package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.AllyState;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.EnemyState;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.MercenaryState;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.entities.staticobject.wall.Wall;

public class Mercenary extends NonPlayableActor {

    MercenaryState enemyState;
    MercenaryState allyState;
    MercenaryState currentState;

    public Mercenary() {
        this.enemyState = new EnemyState(this);
        this.allyState = new AllyState(this);
        this.currentState = enemyState;
    }

    public void doAccept(Player player) {
        player.visit(this);
    }

    public void visit(Portal portal) {
        //TODO
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

    public boolean isAlly() {
        return this.currentState.isAlly();
    }

    @Override
    public void update(MovementBehaviour movementBehaviour) {
        this.setCurrentMovement(movementBehaviour);
        this.doMove(this);
    }

    @Override
    public boolean isInteractable() {
        return this.currentState.canInteract();
    }

    @Override
    public boolean canVisitWall() {
        return false;
    }


}
