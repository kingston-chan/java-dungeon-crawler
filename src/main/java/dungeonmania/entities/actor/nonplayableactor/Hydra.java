package dungeonmania.entities.actor.nonplayableactor;

import java.util.Random;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;

public class Hydra extends SpecialCreature {
    private Random battleHealRandom;
    private double healRate;
    private double healAmount;

    public Hydra() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        healAmount = dungeon.getDoubleConfig("hydra_health_increase_amount");
        healRate = dungeon.getDoubleConfig("hydra_health_increase_rate");
    }

    public void setRandomHeal() {
        battleHealRandom = new Random();
    }

    @Override
    public void doAccept(Player player) {
        player.visit(this);
    }

    @Override
    public void visit(Player player) {
        setRandomHeal();
        super.visit(player);
    }

    @Override
    public double attackedBy(Player player) {
        if (battleHealRandom.nextDouble() < healRate) {
            heal(healAmount);
            return healAmount;
        }
        return super.attackedBy(player);
    }

    @Override
    public void visitInvisiblePlayer(Player player) {
    }
}
