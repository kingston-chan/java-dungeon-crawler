package dungeonmania.factory;

import org.json.JSONObject;

public interface DungeonObjectFactory extends java.io.Serializable {
    public void create(JSONObject dungeonObject);
}
