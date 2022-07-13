package dungeonmania.entities.actor.player.states;

import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;

public interface PlayerState {
    public void visitSpider(Spider spider);

    public void visitMercenary(Mercenary mercenary);

    public void visitZombieToast(ZombieToast zombieToast);

    public void acceptNonPlayableActor(NonPlayableActor npa);

    public void notifyNonPlayableActors();
}
