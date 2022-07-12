package dungeonmania.entities.actor.player.states;

import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;

public interface PlayerState {
    public boolean visitSpider(Spider spider);

    public boolean visitMercenary(Mercenary mercenary);

    public boolean visitZombieToast(ZombieToast zombieToast);

    public boolean acceptNonPlayableActor(NonPlayableActor npa);

    public void notifyNonPlayableActors();
}
