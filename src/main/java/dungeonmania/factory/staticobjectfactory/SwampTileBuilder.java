package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.swamptile.SwampTile;
import dungeonmania.factory.FactoryHelpers;

public class SwampTileBuilder implements StaticObjectBuilder {
    @Override
    public void buildStaticObject(JSONObject staticObject) {
        SwampTile swampTile = new SwampTile(FactoryHelpers.extractMovementFactor(staticObject));
        swampTile.setPosition(FactoryHelpers.extractPosition(staticObject));
        swampTile.setType(FactoryHelpers.extractType(staticObject));
        swampTile.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(swampTile.getUniqueId(), swampTile);
    }
}
