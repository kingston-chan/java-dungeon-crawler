package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.exit.Exit;
import dungeonmania.util.Position;

public class ExitBuilder implements StaticObjectBuilder {

    @Override
    public void buildStaticObject(Position position, String type, String portalColour, int key) {
        Exit exit = new Exit();
        exit.setPosition(position);
        exit.setUniqueId(UUID.randomUUID().toString());
        exit.setType(type);
        DungeonManiaController.getDungeon().addDungeonObject(exit.getUniqueId(), exit);
    }

}
