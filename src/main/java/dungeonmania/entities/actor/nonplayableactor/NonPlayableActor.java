package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.Actor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.battle.Battle;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.entities.staticobject.portal.Portal;

public abstract class NonPlayableActor extends Actor {
    private MovementBehaviour defaultMovement;
    private MovementBehaviour currentMovement;
    private int stuckTicks = 0;

    public boolean canAccept(Boulder boulder) {
        return false;
    }

    public MovementBehaviour getDefaultMovement() {
        return defaultMovement;
    }

    public void setDefaultMovement(MovementBehaviour movementBehaviour) {
        this.defaultMovement = movementBehaviour;
    }

    public MovementBehaviour getCurrentMovement() {
        return currentMovement;
    }

    public void setCurrentMovement(MovementBehaviour movementBehaviour) {
        this.currentMovement = movementBehaviour;
    }

    public void doMove() {
        this.currentMovement.move(this);
    }

    public void stuck(int stuckTicks) {
        this.stuckTicks = stuckTicks;
    }

    public boolean isStuck() {
        if (this.stuckTicks > 0) {
            this.stuckTicks--;
            return true;
        }
        return false;
    }

    public double attackedBy(Player player) {
        Dungeon dungeon = DungeonManiaController.getDungeon();

        int allyAttack = dungeon.getIntConfig("ally_attack");

        int totalBonusAttack = player.getBonusMultiplicativeAttack()
                * (player.getAttackPoints() + player.getBonusAdditiveAttack() + (player.getNumAllies() * allyAttack));

        double playerDamage = (totalBonusAttack / 5.0);

        takeDamage(playerDamage);

        return -playerDamage;
    }

    @Override
    public void visit(Player player) {
        Battle battle = new Battle(getType(), getHealthPoints(), player.getHealthPoints());
        battle.simulateNormalBattle(player, this);
    }

    public abstract boolean canVisitWall();

    public abstract boolean canVisitPortal(Portal portal);

    public abstract boolean canVisitDoor(Door door);

    public abstract void movePlayerIsNormal();

    public abstract void movePlayerIsInvisible();

    public abstract void movePlayerIsInvincible();

    public abstract void visitInvisiblePlayer(Player player);
}
