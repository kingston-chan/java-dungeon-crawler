package dungeonmania.factory.actorfactory;

import java.util.HashMap;
import java.util.Map;

import dungeonmania.factory.DungeonObjectFactory;
import dungeonmania.util.Position;

public class ActorFactory implements DungeonObjectFactory {
    private Map<String, ActorBuilder> actorBuilders = new HashMap<>();

    public ActorFactory() {
        this.actorBuilders.put("player", new PlayerBuilder());
        this.actorBuilders.put("mercenary", new MercenaryBuilder());
        this.actorBuilders.put("zombie_toast", new ZombieToastBuilder());
        this.actorBuilders.put("spider", new SpiderBuilder());
    }

    @Override
    public void create(Position position, String type, String portalColour, int key) {
        ActorBuilder actorBuilder = this.actorBuilders.get(type);
        actorBuilder.buildActor(position, type);
    }
}
