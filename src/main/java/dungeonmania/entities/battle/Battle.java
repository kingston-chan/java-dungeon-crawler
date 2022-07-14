package dungeonmania.entities.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.equipment.Equipment;

public class Battle {
    private List<Round> rounds = new ArrayList<>();
    private String enemyType;
    private double initialEnemyHealth;
    private double initialPlayerHealth;

    public Battle(String enemyType, double initialEnemyHealth, double initialPlayerHealth) {
        this.enemyType = enemyType;
        this.initialEnemyHealth = initialEnemyHealth;
        this.initialPlayerHealth = initialPlayerHealth;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void addRound(Round round) {
        this.rounds.add(round);
    }

    public String getEnemyType() {
        return enemyType;
    }

    public double getInitialEnemyHealth() {
        return initialEnemyHealth;
    }

    public double getInitialPlayerHealth() {
        return initialPlayerHealth;
    }

    public void simulateNormalBattle(Player player, NonPlayableActor npa) {
        player.resetBonusStats();

        Dungeon dungeon = DungeonManiaController.getDungeon();
        List<Item> equipmentUsed = player.getInventory().stream()
                .filter(item -> item instanceof Equipment)
                .collect(Collectors.toList());
        equipmentUsed.forEach(item -> ((Equipment) item).playerEquip(player));

        int allyAttack = dungeon.getConfig("ally_attack");
        int allyDefence = dungeon.getConfig("ally_defence");

        int totalBonusAttack = player.getBonusMultiplicativeAttack()
                * (player.getAttackPoints() + player.getBonusAdditiveAttack() + (player.getNumAllies() * allyAttack));

        double playerDamage = (totalBonusAttack / 5.0);

        int totalBonusDefence = player.getBonusAdditiveDefence() + (player.getNumAllies() * allyDefence);

        double npaDamage = (npa.getAttackPoints() - totalBonusDefence) / 10.0;

        npaDamage = npaDamage < 0 ? 0 : npaDamage;

        while (player.getHealthPoints() > 0 && npa.getHealthPoints() > 0) {
            player.takeDamage(npaDamage);
            npa.takeDamage(playerDamage);
            this.addRound(new Round(-(npaDamage), -(playerDamage), equipmentUsed));
        }

        if (npa.getHealthPoints() <= 0) {
            dungeon.removeDungeonObject(npa.getUniqueId());
            player.defeatedEnemy();
        }

        if (player.getHealthPoints() <= 0) {
            dungeon.removeDungeonObject(player.getUniqueId());
        }

        dungeon.addToBattles(this);
    }
}
