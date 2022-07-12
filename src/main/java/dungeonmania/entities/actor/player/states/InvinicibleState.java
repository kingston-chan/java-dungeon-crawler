package dungeonmania.entities.actor.player.states;

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

public class InvinicibleState implements PlayerState {

    private MovementBehaviour movementBehaviour = new MoveAwayFromPlayer();
    private Player player;

    public InvinicibleState(Player player) {
        this.player = player;
    }

    private void doInvinicbleBattle(NonPlayableActor npa) {
        Battle battle = new Battle(npa.getType(), npa.getHealthPoints(), player.getHealthPoints());
        Round round = new Round(0, -npa.getHealthPoints());
        round.addWeaponsUsed(player.getPotionConsumed());
        Dungeon dungeon = DungeonManiaController.getDungeon();
        dungeon.removeDungeonObject(npa.getUniqueId());
        dungeon.addToBattles(battle);
    };

    @Override
    public boolean visitSpider(Spider spider) {
        doInvinicbleBattle(spider);
        return true;
    }

    @Override
    public boolean visitMercenary(Mercenary mercenary) {
        doInvinicbleBattle(mercenary);
        return true;
    }

    @Override
    public boolean visitZombieToast(ZombieToast zombieToast) {
        doInvinicbleBattle(zombieToast);
        return true;
    }

    @Override
    public boolean acceptNonPlayableActor(NonPlayableActor npa) {
        doInvinicbleBattle(npa);
        return true;
    }

    @Override
    public void notifyNonPlayableActors() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        dungeon.getNonPlayableActors().stream().forEach(npa -> npa.update(movementBehaviour));
    }
}
