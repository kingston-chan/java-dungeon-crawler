package dungeonmania.entities.actor;

import dungeonmania.behaviours.visitor.VisitorBehaviour;
import dungeonmania.entities.DungeonObject;

public abstract class Actor extends DungeonObject {
    private VisitorBehaviour visitorBehaviour;
    private double healthPoints;
    private int attackPoints;
    private int defencePoints;

    public void setVisitorBehaviour(VisitorBehaviour visitorBehaviour) {
        this.visitorBehaviour = visitorBehaviour;
    }

    public double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefencePoints() {
        return defencePoints;
    }

    public void setDefencePoints(int defencePoints) {
        this.defencePoints = defencePoints;
    }
}