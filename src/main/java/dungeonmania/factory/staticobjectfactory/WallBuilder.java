package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.wall.Wall;
import dungeonmania.factory.FactoryHelpers;

public class WallBuilder implements StaticObjectBuilder {
    @Override
    public void buildStaticObject(JSONObject staticObject) {
        Wall wall = new Wall();
        wall.setPosition(FactoryHelpers.extractPosition(staticObject));
        wall.setType(FactoryHelpers.extractType(staticObject));
        wall.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(wall.getUniqueId(), wall);
    }
}
