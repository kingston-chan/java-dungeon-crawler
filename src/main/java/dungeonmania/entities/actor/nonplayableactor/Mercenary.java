package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.MercenaryState;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.MindControlState;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.battle.Battle;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.util.Position;

public class Mercenary extends NonPlayableActor {

    MercenaryState enemyState;
    MercenaryState allyState;
    MercenaryState currentState;
    MercenaryState mindcontrolState;

    public void setEnemyState(MercenaryState enemyState) {
        this.enemyState = enemyState;
        this.currentState = enemyState;
    }

    public void setAllyState(MercenaryState allyState) {
        this.allyState = allyState;
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

    public MercenaryState getMindcontrolState() {
        return this.mindcontrolState;
    }

    public boolean isAlly() {
        return this.currentState.isAlly();
    }

    public void recruitedBy(Player player) {
        this.currentState.recruitedBy(player);
    }

    public MercenaryState getCurrentState() {
        return this.currentState;
    }

    public void mindcontrol() {
        this.currentState.mindcontrol();
    }

    public int getBribeAmount() {
        return this.currentState.bribeAmount();
    }

    public boolean isAssassin() {
        return this.currentState.isAssassin();
    }

    @Override
    public void update(MovementBehaviour movementBehaviour) {
        if (getStuckTicks() > 0) {
            reduceStuckTick();
            return;
        }
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

    @Override
    public boolean canVisitPortal(Portal portal) {
        return portal.getExitPosition(getPosition()) != null;
    }

    @Override
    public boolean canVisitDoor(Door door) {
        return door.isOpened();
    }

    @Override
    public void visit(Player player) {
        Battle battle = new Battle(this.getType(), this.getHealthPoints(), player.getHealthPoints());
        battle.simulateNormalBattle(player, this);
    }

    @Override
    public void visitInvisiblePlayer(Player player) {
        this.currentState.visitInvisiblePlayer(player);
    }
}
