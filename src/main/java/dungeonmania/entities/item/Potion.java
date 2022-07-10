package dungeonmania.entities.item;

import dungeonmania.behaviours.automatedmovement.AutomatedMovementBehaviour;
import dungeonmania.behaviours.host.HostBehaviour;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;

public class Potion extends Item {
    private HostBehaviour playerHostEffect;
    private HostBehaviour enemyHostEffect;
    private AutomatedMovementBehaviour enemyMovementEffect;
    private int duration;

    public Potion(HostBehaviour playerHostEffect, HostBehaviour enemyHostEffect,
            AutomatedMovementBehaviour enemyMovementEffect, int duration) {
        this.playerHostEffect = playerHostEffect;
        this.enemyHostEffect = enemyHostEffect;
        this.enemyMovementEffect = enemyMovementEffect;
        this.duration = duration;
    }

    public HostBehaviour getPlayerHostEffect() {
        return playerHostEffect;
    }

    public HostBehaviour getEnemyHostEffect() {
        return enemyHostEffect;
    }

    public AutomatedMovementBehaviour getEnemyMovementEffect() {
        return enemyMovementEffect;
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public boolean playerUse(Player player, Dungeon dungeon) {
        player.usePotion(this);
        return true;
    }
}
