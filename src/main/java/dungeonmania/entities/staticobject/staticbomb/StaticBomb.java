package dungeonmania.entities.staticobject.staticbomb;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.util.BombHelper;
import dungeonmania.entities.staticobject.floorswitch.SwitchSubject;

public class StaticBomb extends StaticObject implements SwitchObserver {

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void update() {
        BombHelper.explode(DungeonManiaController.getDungeon(), this.getPosition());
    }
}
