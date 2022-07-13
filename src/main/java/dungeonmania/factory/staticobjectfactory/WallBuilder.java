package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.wall.Wall;
import dungeonmania.util.Position;

public class WallBuilder implements StaticObjectBuilder {

    @Override
    public void buildStaticObject(Position position, String type, String portalColour, int key) {
        Wall wall = new Wall();
        wall.setPosition(position);
        wall.setType(type);
        wall.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(wall.getUniqueId(), wall);
    }

}
