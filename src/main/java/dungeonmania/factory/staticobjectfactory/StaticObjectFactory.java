package dungeonmania.factory.staticobjectfactory;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import dungeonmania.factory.DungeonObjectFactory;

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
        this.staticObjectFactory.put("swamp_tile", new SwampTileBuilder());
        this.staticObjectFactory.put("wire", new WireBuilder());
        this.staticObjectFactory.put("switch_door", new SwitchDoorBuilder());
        this.staticObjectFactory.put("light_bulb_off", new LightbulbBuilder());
    }

    @Override
    public void create(JSONObject dungeonObject) {
        StaticObjectBuilder staticObjectBuilder = this.staticObjectFactory.get(dungeonObject.getString("type"));
        staticObjectBuilder.buildStaticObject(dungeonObject);
    }
}
