package dungeonmania.entities.actor.player.buildables;

import java.util.HashMap;
import java.util.Map;

public class Buildables implements java.io.Serializable {
    private Map<String, BuildableBlueprint> blueprints = new HashMap<>();

    public Buildables() {
        blueprints.put("bow", new BowBlueprint());
        blueprints.put("shield", new ShieldBlueprint());
        blueprints.put("midnight_armour", new MidnightArmourBlueprint());
        blueprints.put("sceptre", new SceptreBlueprint());
    }

    public BuildableBlueprint getBlueprint(String itemType) {
        return this.blueprints.get(itemType);
    }
}
