package dungeonmania.factory.actorfactory;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import dungeonmania.factory.DungeonObjectFactory;
import dungeonmania.factory.FactoryHelpers;

public class ActorFactory implements DungeonObjectFactory {
    private Map<String, ActorBuilder> actorBuilders = new HashMap<>();

    public ActorFactory() {
        this.actorBuilders.put("player", new PlayerBuilder());
        this.actorBuilders.put("mercenary", new MercenaryBuilder());
        this.actorBuilders.put("zombie_toast", new ZombieToastBuilder());
        this.actorBuilders.put("spider", new SpiderBuilder());
        this.actorBuilders.put("assassin", new AssassinBuilder());
        this.actorBuilders.put("hydra", new HydraBuilder());
    }

    @Override
    public void create(JSONObject actor) {
        ActorBuilder actorBuilder = this.actorBuilders.get(FactoryHelpers.extractType(actor));
        actorBuilder.buildActor(actor);
    }
}
