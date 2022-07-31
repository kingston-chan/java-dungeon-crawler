package dungeonmania.factory;

import org.json.JSONObject;

import dungeonmania.util.Position;

public class FactoryHelpers {
    public static Position extractPosition(JSONObject jsonObject) {
        return new Position(jsonObject.getInt("x"), jsonObject.getInt("y"));
    }

    public static String extractType(JSONObject jsonObject) {
        return jsonObject.getString("type");
    }

    public static String extractPortalColour(JSONObject jsonObject) {
        return jsonObject.getString("colour");
    }

    public static int extractKey(JSONObject jsonObject) {
        return jsonObject.getInt("key");
    }

    public static int extractMovementFactor(JSONObject jsonObject) {
        return jsonObject.getInt("movement_factor");
    }

    public static String extractLogic(JSONObject jsonObject) {
        return jsonObject.getString("logic");
    }
}
