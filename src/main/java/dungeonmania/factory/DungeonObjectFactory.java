package dungeonmania.factory;

import dungeonmania.entities.Dungeon;
import dungeonmania.util.Position;

public interface DungeonObjectFactory {
    public void create(Position position, String type, Dungeon dungeon);
}
