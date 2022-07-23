package dungeonmania.entities.staticobject.swamptile;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.staticobject.StaticObject;

public class SwampTile extends StaticObject {
    private int ticksStuckOnTile;

    public SwampTile(int movementFactor) {
        this.ticksStuckOnTile = (movementFactor * 2) - 2;
    }

    @Override
    public void doAccept(NonPlayableActor entity) {
        entity.stuck(ticksStuckOnTile);
    }

    @Override
    public int tickCost() {
        return ticksStuckOnTile;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

}
