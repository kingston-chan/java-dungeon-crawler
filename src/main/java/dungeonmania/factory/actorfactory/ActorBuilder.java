package dungeonmania.factory.actorfactory;

import org.json.JSONObject;

public interface ActorBuilder extends java.io.Serializable {
    public void buildActor(JSONObject actor);
}
