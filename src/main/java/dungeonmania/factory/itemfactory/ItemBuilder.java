package dungeonmania.factory.itemfactory;

import org.json.JSONObject;

public interface ItemBuilder extends java.io.Serializable {
    public void buildItem(JSONObject item);
}
