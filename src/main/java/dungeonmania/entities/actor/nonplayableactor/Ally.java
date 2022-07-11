package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.automatedmovement.AutomatedMovementBehaviour;
import dungeonmania.behaviours.host.HostBehaviour;

public class Ally extends NonPlayableActor {

    @Override
    public void update(AutomatedMovementBehaviour movement, HostBehaviour hostBehaviour) {
        
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
}
