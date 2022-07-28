package dungeonmania.factory;

import java.util.HashMap;
import java.util.Map;

import dungeonmania.factory.actorfactory.ActorFactory;
import dungeonmania.factory.itemfactory.ItemFactory;
import dungeonmania.factory.staticobjectfactory.StaticObjectFactory;

public class FactoryChooser implements java.io.Serializable {
    private Map<String, DungeonObjectFactory> factoryChooser = new HashMap<>();

    public FactoryChooser() {
        // actors
        this.factoryChooser.put("player", new ActorFactory());
        this.factoryChooser.put("spider", new ActorFactory());
        this.factoryChooser.put("mercenary", new ActorFactory());
        this.factoryChooser.put("zombie_toast", new ActorFactory());
        this.factoryChooser.put("assassin", new ActorFactory());
        this.factoryChooser.put("hydra", new ActorFactory());
        // static objects
        this.factoryChooser.put("wire", new StaticObjectFactory());
        this.factoryChooser.put("switch_door", new StaticObjectFactory());
        this.factoryChooser.put("wall", new StaticObjectFactory());
        this.factoryChooser.put("exit", new StaticObjectFactory());
        this.factoryChooser.put("boulder", new StaticObjectFactory());
        this.factoryChooser.put("switch", new StaticObjectFactory());
        this.factoryChooser.put("switch_door", new StaticObjectFactory());
        this.factoryChooser.put("light_bulb_off", new StaticObjectFactory());
        this.factoryChooser.put("door", new StaticObjectFactory());
        this.factoryChooser.put("portal", new StaticObjectFactory());
        this.factoryChooser.put("zombie_toast_spawner", new StaticObjectFactory());
        this.factoryChooser.put("swamp_tile", new StaticObjectFactory());
        // items
        this.factoryChooser.put("treasure", new ItemFactory());
        this.factoryChooser.put("sun_stone", new ItemFactory());
        this.factoryChooser.put("key", new ItemFactory());
        this.factoryChooser.put("invincibility_potion", new ItemFactory());
        this.factoryChooser.put("invisibility_potion", new ItemFactory());
        this.factoryChooser.put("wood", new ItemFactory());
        this.factoryChooser.put("arrow", new ItemFactory());
        this.factoryChooser.put("bomb", new ItemFactory());
        this.factoryChooser.put("sword", new ItemFactory());
    }

    public DungeonObjectFactory getFactory(String type) {
        return this.factoryChooser.get(type);
    }
}
