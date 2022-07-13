package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.automatedmovement.MovementBehaviour;
import dungeonmania.entities.actor.player.Player;

public class ZombieToast extends NonPlayableActor {

    public void doAccept(Player player) {
        
    }

    @Override
    public void update(MovementBehaviour movementBehaviour) {
        this.setCurrentMovement(movementBehaviour);
    }   

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public boolean canVisitWall() {
        return false;
    }


}
