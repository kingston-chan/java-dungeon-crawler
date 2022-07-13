package dungeonmania.factory.actorfactory;

import dungeonmania.entities.DungeonObject;
import dungeonmania.util.Position;

public interface ActorBuilder {
    public void buildActor(Position position, String type);
}
