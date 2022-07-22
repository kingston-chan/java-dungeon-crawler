package dungeonmania.entities.staticobject.swamptile;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.staticobject.StaticObject;

public class SwampTile extends StaticObject {
    private int movementFactor;

    public SwampTile(int movementFactor) {
        this.movementFactor = movementFactor;
    }

    @Override
    public void doAccept(NonPlayableActor entity) {
        entity.stuck(movementFactor);
    }

    @Override
    public int tickCost() {
        return movementFactor;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

}
