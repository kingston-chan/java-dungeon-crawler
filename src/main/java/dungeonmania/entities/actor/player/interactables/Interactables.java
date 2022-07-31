package dungeonmania.entities.actor.player.interactables;

import java.util.HashMap;
import java.util.Map;

public class Interactables implements  java.io.Serializable {
    private Map<String, InteractBehaviour> interactions = new HashMap<>();

    public Interactables() {
        interactions.put("mercenary", new MercenaryInteract());
        interactions.put("assassin", new MercenaryInteract());
        interactions.put("zombie_toast_spawner", new ZombieSpawnerInteract());
    }

    public InteractBehaviour getInteraction(String entityType) {
        return this.interactions.get(entityType);
    }
}
