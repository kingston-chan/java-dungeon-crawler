package dungeonmania.entities.actor.player.states;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MoveRandomly;
import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.player.Player;

public class InvisibleState implements PlayerState {

    private Player player;
    private MovementBehaviour movementBehaviour = new MoveRandomly();

    public InvisibleState(Player player) {
        this.player = player;
    }

    @Override
    public boolean visitSpider(Spider spider) {
        return true;
    }

    @Override
    public boolean visitMercenary(Mercenary mercenary) {
        return true;
    }

    @Override
    public boolean visitZombieToast(ZombieToast zombieToast) {
        return true;
    }

    @Override
    public boolean acceptNonPlayableActor(NonPlayableActor npa) {
        return true;
    }

    @Override
    public void notifyNonPlayableActors() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        dungeon.getNonPlayableActors().stream().forEach(npa -> npa.update(movementBehaviour));
    }

}
