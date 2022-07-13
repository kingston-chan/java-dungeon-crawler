package dungeonmania.factory.staticobjectfactory;

import java.util.HashMap;
import java.util.Map;

import dungeonmania.entities.DungeonObject;
import dungeonmania.factory.DungeonObjectFactory;
import dungeonmania.util.Position;

public class StaticObjectFactory implements DungeonObjectFactory {
    private Map<String, StaticObjectBuilder> staticObjectFactory = new HashMap<>();

    public StaticObjectFactory() {
        this.staticObjectFactory.put("wall", new WallBuilder());
        this.staticObjectFactory.put("portal", new PortalBuilder());
        this.staticObjectFactory.put("door", new DoorBuilder());
        this.staticObjectFactory.put("exit", new ExitBuilder());
        this.staticObjectFactory.put("boulder", new BoulderBuilder());
        this.staticObjectFactory.put("switch", new FloorSwitchBuilder());
        this.staticObjectFactory.put("zombie_toast_spawner", new ZombieSpawnerBuilder());

    }

    @Override
    public void create(Position position, String type, String portalColour, int key) {
        StaticObjectBuilder staticObjectBuilder = this.staticObjectFactory.get(type);
        staticObjectBuilder.buildStaticObject(position, type, portalColour, key);
    }
}
