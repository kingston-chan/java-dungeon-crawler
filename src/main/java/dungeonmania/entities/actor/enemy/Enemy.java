package dungeonmania.entities.actor.enemy;

import dungeonmania.entities.actor.Actor;

public class Enemy extends Actor {
    @Override
    public boolean isInteractable() {
        return false;
    }
}
