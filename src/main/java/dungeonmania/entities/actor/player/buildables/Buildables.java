package dungeonmania.entities.actor.player.buildables;

import java.util.HashMap;
import java.util.Map;

public class Buildables {
    private Map<String, BuildableBlueprint> blueprints = new HashMap<>();

    public Buildables() {
        blueprints.put("bow", new BowBlueprint());
        blueprints.put("shield", new ShieldBlueprint());
    }

    public BuildableBlueprint getBlueprint(String itemType) {
        return this.blueprints.get(itemType);
    }
}
