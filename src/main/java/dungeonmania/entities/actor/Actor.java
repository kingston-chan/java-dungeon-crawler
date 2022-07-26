package dungeonmania.entities.actor;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.visitor.Visit;

public abstract class Actor extends DungeonObject implements Visit {
    private double healthPoints;
    private int attackPoints;

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

    public void takeDamage(double damage) {
        this.healthPoints -= damage;
    }

    public void heal(double health) {
        this.healthPoints += health;
    }
}