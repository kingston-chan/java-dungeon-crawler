package dungeonmania.factory;

import dungeonmania.entities.DungeonObject;
import dungeonmania.util.Position;

public interface DungeonObjectFactory {
    public void create(Position position, String type, String portalColour, int key);
}
