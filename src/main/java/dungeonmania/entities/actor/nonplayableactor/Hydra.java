package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.entities.staticobject.portal.Portal;

public class Hydra extends NonPlayableActor{

    @Override
    public boolean canVisitWall() {
        return false;
    }

    @Override
    public boolean canVisitPortal(Portal portal) {
        return true;
    }

    @Override
    public boolean canVisitDoor(Door door) {
        return door.isOpened();
    }

    @Override
    public void update(MovementBehaviour movementBehaviour) {
        // TODO Auto-generated method stub
         
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
    
}
