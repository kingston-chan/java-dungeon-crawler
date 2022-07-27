package dungeonmania.entities.battle;

import java.util.List;

import dungeonmania.entities.item.Item;

public class Round implements java.io.Serializable {
    private double playerHealthChange;
    private double enemyHealthChange;
    private List<Item> playerWeaponsUsed;

    public Round(double playerHealthChange, double enemyHealthChange, List<Item> playerWeaponsUsed) {
        this.playerHealthChange = playerHealthChange;
        this.enemyHealthChange = enemyHealthChange;
        this.playerWeaponsUsed = playerWeaponsUsed;
    }

    public double getPlayerHealthChange() {
        return playerHealthChange;
    }

    public double getEnemyHealthChange() {
        return enemyHealthChange;
    }

    public List<Item> getPlayerWeaponsUsed() {
        return playerWeaponsUsed;
    }
}
