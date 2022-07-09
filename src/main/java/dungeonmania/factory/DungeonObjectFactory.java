package dungeonmania.factory;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.util.Position;

public interface DungeonObjectFactory {
    public DungeonObject create(Position position, String type, Dungeon dungeon, String portalColour, int key);
}
