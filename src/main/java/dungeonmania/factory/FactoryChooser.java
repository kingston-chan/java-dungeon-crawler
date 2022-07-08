package dungeonmania.factory;

import java.util.HashMap;
import java.util.Map;

public class FactoryChooser {
    private Map<String, DungeonObjectFactory> factoryChooser = new HashMap<>();

    public FactoryChooser() {

    }

    public DungeonObjectFactory getFactory(String type) {
        return null;
    }
}
