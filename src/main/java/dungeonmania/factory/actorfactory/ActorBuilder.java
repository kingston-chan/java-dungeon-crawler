package dungeonmania.factory.actorfactory;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.util.Position;

public interface ActorBuilder {
    public DungeonObject buildActor(Position position, String type, Dungeon dungeon);
}
