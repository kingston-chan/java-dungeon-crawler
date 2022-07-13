package dungeonmania.entities.actor.player.states;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MoveAwayFromPlayer;
import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.battle.Battle;
import dungeonmania.entities.battle.Round;
import dungeonmania.entities.item.Item;

public class InvinicibleState implements PlayerState {

    private MovementBehaviour movementBehaviour = new MoveAwayFromPlayer();
    private Player player;

    public InvinicibleState(Player player) {
        this.player = player;
    }

    private void doInvinicbleBattle(NonPlayableActor npa) {
        Battle battle = new Battle(npa.getType(), npa.getHealthPoints(), player.getHealthPoints());
        List<Item> potionUsed = new ArrayList<>();
        potionUsed.add(player.getPotionConsumed());
        Round round = new Round(0, -npa.getHealthPoints(), potionUsed);
        Dungeon dungeon = DungeonManiaController.getDungeon();
        dungeon.removeDungeonObject(npa.getUniqueId());
        battle.addRound(round);
        dungeon.addToBattles(battle);
        player.defeatedEnemy();
    };

    @Override
    public void visitSpider(Spider spider) {
        doInvinicbleBattle(spider);
    }

    @Override
    public void visitMercenary(Mercenary mercenary) {
        if (mercenary.isAlly()) {
            return;
        }
        doInvinicbleBattle(mercenary);
    }

    @Override
    public void visitZombieToast(ZombieToast zombieToast) {
        doInvinicbleBattle(zombieToast);
    }

    @Override
    public void acceptNonPlayableActor(NonPlayableActor npa) {
        doInvinicbleBattle(npa);
    }

    @Override
    public void notifyNonPlayableActors() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        dungeon.getNonPlayableActors().stream().forEach(npa -> npa.update(movementBehaviour));
    }
}
