package dungeonmania.factory.staticobjectfactory;

import org.json.JSONObject;

public interface StaticObjectBuilder extends java.io.Serializable {
    public void buildStaticObject(JSONObject staticObject);
}
