package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.automatedmovement.MovementBehaviour;

public class Spider extends NonPlayableActor {
    @Override
    public void update(MovementBehaviour movementBehaviour) {

    }

    @Override
    public boolean isInteractable() {
        return false;
    }

}
