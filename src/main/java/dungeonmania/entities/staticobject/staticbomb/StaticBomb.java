package dungeonmania.entities.staticobject.staticbomb;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.SwitchObserver;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.util.BombHelper;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.floorswitch.SwitchSubject;

public class StaticBomb extends StaticObject implements SwitchObserver {

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void update(SwitchSubject switchSubject) {
        switchSubject.remove(this);
        BombHelper.explode(DungeonManiaController.getDungeon(), this.getPosition());
    }
}
