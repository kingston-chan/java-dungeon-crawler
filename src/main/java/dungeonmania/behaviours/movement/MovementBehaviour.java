package dungeonmania.behaviours.movement;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;

public interface MovementBehaviour extends java.io.Serializable {

    public void move(NonPlayableActor npa);

}
