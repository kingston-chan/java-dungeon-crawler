package dungeonmania.entities.actor.player.states;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.player.Player;

public class NormalState implements PlayerState {
    private Player player;

    public NormalState(Player player) {
        this.player = player;
    }

    @Override
    public boolean visitSpider(Spider spider) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean visitMercenary(Mercenary mercenary) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean visitZombieToast(ZombieToast zombieToast) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean acceptNonPlayableActor(NonPlayableActor npa) {
        // TODO Auto-generated method stub
        return npa.visit(player);
    }

    @Override
    public void notifyNonPlayableActors() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        dungeon.getNonPlayableActors().stream().forEach(npa -> npa.update(npa.getDefaultMovement()));
    }

}
