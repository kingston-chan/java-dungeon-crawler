package dungeonmania.behaviours.automatedmovement;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.util.Position;

public interface AutomatedMovementBehaviour {

    public Position move(Position position, Dungeon dungeon, NonPlayableActor actor);

}   
