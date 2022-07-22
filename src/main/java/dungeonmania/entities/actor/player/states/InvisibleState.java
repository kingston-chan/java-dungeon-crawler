package dungeonmania.entities.actor.player.states;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MoveRandomly;
import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Hydra;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.AssassinState;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.battle.Battle;

public class InvisibleState implements PlayerState {

    private Player player;
    private MovementBehaviour movementBehaviour = new MoveRandomly();

    public InvisibleState(Player player) {
        this.player = player;
    }

    @Override
    public void visitSpider(Spider spider) {
    }

    @Override
    public void visitMercenary(Mercenary mercenary) {
        // check if mercenary is assassin or not
        if (mercenary.getCurrentState() instanceof AssassinState) {
            Battle battle = new Battle(mercenary.getType(), mercenary.getHealthPoints(), player.getHealthPoints());
            battle.simulateNormalBattle(player, mercenary);
        }
    }

    @Override
    public void visitZombieToast(ZombieToast zombieToast) {
    }

    @Override
    public void acceptNonPlayableActor(NonPlayableActor npa) {
    }

    @Override
    public void notifyNonPlayableActors() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        dungeon.getNonPlayableActors().stream().forEach(npa -> npa.update(movementBehaviour));
    }

    @Override
    public void visitHydra(Hydra hydra) {
    }

}
