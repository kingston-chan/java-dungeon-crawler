package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.AllyState;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.EnemyState;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.MercenaryState;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.portal.Portal;<<<<<<<HEAD=======
import dungeonmania.util.Position;>>>>>>>252 b9a9d00046afce33422b39310af9083e72f4e

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
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Position destinationPortalPosition = portal.getDestination();
        Position exitPosition = portal.getExitPosition(getPosition());
        setPosition(destinationPortalPosition);
        dungeon.getStaticObjectsAtPosition(exitPosition).stream()
                .forEach(o -> o.doAccept(this));
        if (destinationPortalPosition == getPosition()) {
            if (dungeon.getPlayer().getPosition().equals(exitPosition)) {
                dungeon.getPlayer().doAccept(this);
            }
            setPosition(exitPosition);
        }
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
        this.currentState.updateMovement(movementBehaviour);
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

    <<<<<<<HEAD

    =======>>>>>>>252 b9a9d00046afce33422b39310af9083e72f4e
}
