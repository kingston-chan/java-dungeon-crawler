package dungeonmania.behaviours.automatedmovement;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.util.Position;

public interface MovementBehaviour {

    public void move(NonPlayableActor npa);

}
