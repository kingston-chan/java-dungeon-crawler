package dungeonmania.entities.actor.player.states;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Hydra;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.battle.Battle;

public class NormalState implements PlayerState {
    private Player player;

    public NormalState(Player player) {
        this.player = player;
    }

    @Override
    public void visitSpider(Spider spider) {
        Battle battle = new Battle(spider.getType(), spider.getHealthPoints(), player.getHealthPoints());
        battle.simulateNormalBattle(player, spider);
    }

    @Override
    public void visitMercenary(Mercenary mercenary) {
        if (mercenary.isAlly()) {
            return;
        }
        Battle battle = new Battle(mercenary.getType(), mercenary.getHealthPoints(), player.getHealthPoints());
        battle.simulateNormalBattle(player, mercenary);
    }

    @Override
    public void visitZombieToast(ZombieToast zombieToast) {
        Battle battle = new Battle(zombieToast.getType(), zombieToast.getHealthPoints(), player.getHealthPoints());
        battle.simulateNormalBattle(player, zombieToast);
    }

    @Override
    public void acceptNonPlayableActor(NonPlayableActor npa) {
        npa.visit(player);
    }

    @Override
    public void notifyNonPlayableActors() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        dungeon.getNonPlayableActors().stream().forEach(npa -> npa.movePlayerIsNormal());
    }

    @Override
    public void visitHydra(Hydra hydra) {
        Battle battle = new Battle(hydra.getType(), hydra.getHealthPoints(), player.getHealthPoints());
        hydra.setRandomHeal();
        battle.simulateNormalBattle(player, hydra);
    }
}
