package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.zombietoastspawner.ZombieToastSpawner;
import dungeonmania.factory.FactoryHelpers;

public class ZombieSpawnerBuilder implements StaticObjectBuilder {
    @Override
    public void buildStaticObject(JSONObject staticObject) {
        ZombieToastSpawner zombieToastSpawner = new ZombieToastSpawner();
        zombieToastSpawner.setPosition(FactoryHelpers.extractPosition(staticObject));
        zombieToastSpawner.setType(FactoryHelpers.extractType(staticObject));
        zombieToastSpawner.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(zombieToastSpawner.getUniqueId(), zombieToastSpawner);
    }

}
