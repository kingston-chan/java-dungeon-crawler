package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.zombietoastspawner.ZombieToastSpawner;
import dungeonmania.util.Position;

public class ZombieSpawnerBuilder implements StaticObjectBuilder {

    @Override
    public void buildStaticObject(Position position, String type, String portalColour, int key) {
        ZombieToastSpawner zombieToastSpawner = new ZombieToastSpawner();
        zombieToastSpawner.setPosition(position);
        zombieToastSpawner.setType(type);
        zombieToastSpawner.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(zombieToastSpawner.getUniqueId(), zombieToastSpawner);
    }

}
