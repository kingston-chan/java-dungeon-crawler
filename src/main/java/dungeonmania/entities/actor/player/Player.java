package dungeonmania.entities.actor.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.eclipse.jetty.io.ssl.SslConnection.DecryptedEndPoint;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.Actor;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.player.buildables.BuildableBlueprint;
import dungeonmania.entities.actor.player.buildables.Buildables;
import dungeonmania.entities.actor.player.interactables.InteractBehaviour;
import dungeonmania.entities.actor.player.interactables.Interactables;
import dungeonmania.entities.actor.player.interactables.ZombieSpawnerInteract;
import dungeonmania.entities.actor.player.states.InvinicibleState;
import dungeonmania.entities.actor.player.states.InvisibleState;
import dungeonmania.entities.actor.player.states.NormalState;
import dungeonmania.entities.actor.player.states.PlayerState;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.collectables.Key;
import dungeonmania.entities.item.potions.Potion;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.entities.staticobject.zombietoastspawner.ZombieToastSpawner;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends Actor {
    private Map<String, Item> inventory = new HashMap<>();
    private Queue<Potion> potions = new LinkedList<>();
    private Potion potionConsumed = null;

    private Buildables buildables = new Buildables();
    private Interactables interactables = new Interactables();

    private int numAllies = 0;
    private int enemiesDefeated = 0;

    private int bonusAdditiveAttack = 0;
    private int bonusMultiplicativeAttack = 1;
    private int bonusAdditiveDefence = 0;

    private PlayerState normalState;
    private PlayerState invisibleState;
    private PlayerState invinicibleState;
    private PlayerState currentState;

    private Position previousPosition;

    public Player() {
        this.normalState = new NormalState(this);
        this.invisibleState = new InvisibleState(this);
        this.invinicibleState = new InvinicibleState(this);
        this.currentState = this.normalState;
    }

    public List<Item> getInventory() {
        return new ArrayList<>(this.inventory.values());
    }

    public void addToInventory(Item item) {
        this.inventory.put(item.getUniqueId(), item);
    }

    public void removeFromInventory(Item item) {
        this.inventory.remove(item.getUniqueId());
    }

    public boolean hasInInventory(String itemId) {
        return this.inventory.get(itemId) != null;
    }

    /**
     * @precond itemId exists in inventory
     * @param itemId
     * @return
     */
    public boolean use(String itemId) {
        return this.inventory.get(itemId).playerUse(this);
    }

    public Key getKey() {
        try {
            return this.inventory.values().stream()
                    .filter(item -> item instanceof Key)
                    .map(key -> (Key) key)
                    .findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

    public void tryPickUpKey(Key key) {
        if (getKey() == null) {
            addToInventory(key);
        }
    }

    public void consumeQueuedPotionEffect() {
        this.potionConsumed = this.potions.poll();
    }

    public void notifyAllObservers() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        this.currentState.notifyNonPlayableActors();
        dungeon.getStaticObjects().stream()
                .filter(staticObject -> staticObject instanceof ZombieToastSpawner)
                .forEach(zombieSpawner -> ((ZombieToastSpawner) zombieSpawner).updateSpawnRate());
        dungeon.updateSpawnSpider();
    }

    public void usePotion(Potion potion) {
        for (int i = 0; i < potion.getDuration(); i++) {
            this.potions.add(potion);
        }
    }

    /**
     * @precond unique id is interactable
     * @param dungeon
     * @param uniqueId
     * @return whether interact was successful
     */
    public boolean interact(Dungeon dungeon, String uniqueId) {
        DungeonObject dungeonObject = dungeon.getDungeonObject(uniqueId);
        InteractBehaviour interaction = interactables.getInteraction(dungeonObject.getType());
        return interaction.interact(this, uniqueId);
    }

    public boolean isValidBuildable(String itemName) {
        return buildables.getBlueprint(itemName) != null;
    }

    /**
     * @precond gives a valid buildable item
     * @param buildableItem
     * @return returns whether valid buildable item can be built
     */
    public boolean checkBuildables(String buildableItem) {
        BuildableBlueprint bp = buildables.getBlueprint(buildableItem);
        return bp.canPlayerBuild(this);
    }

    /**
     * @precond itemType is valid and buildable
     * @param dungeon
     * @param itemType
     */
    public void build(String itemType) {
        BuildableBlueprint bp = buildables.getBlueprint(itemType);
        bp.playerBuild(this);
    }

    public Potion getPotionConsumed() {
        return this.potionConsumed;
    }

    public void addAlly() {
        this.numAllies++;
    }

    public int getNumAllies() {
        return this.numAllies;
    }

    public int getEnemiesDefeated() {
        return this.enemiesDefeated;
    }

    public void defeatedEnemy() {
        this.enemiesDefeated++;
    }

    public void increaseAdditiveAttack(int attackPoints) {
        this.bonusAdditiveAttack += attackPoints;
    }

    public void increaseMultiplicativeAttack(int attackPoints) {
        this.bonusAdditiveAttack *= attackPoints;
    }

    public void increaseAdditiveDefence(int defencePoints) {
        this.bonusAdditiveDefence += defencePoints;
    }

    public int getBonusAdditiveAttack() {
        return this.bonusAdditiveAttack;
    }

    public int getBonusAdditiveDefence() {
        return this.bonusAdditiveDefence;
    }

    public int getBonusMultiplicativeAttack() {
        return this.bonusMultiplicativeAttack;
    }

    public void resetBonusStats() {
        this.bonusAdditiveAttack = 0;
        this.bonusMultiplicativeAttack = 1;
        this.bonusAdditiveDefence = 0;
    }

    @Override
    public void doAccept(NonPlayableActor npa) {
        this.currentState.acceptNonPlayableActor(npa);
    }

    @Override
    public void visit(Item item) {
        addToInventory(item);
    }

    @Override
    public void visit(Portal portal) {

    }

    @Override
    public void visit(Boulder boulder) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        int move_x = getPosition().getX() - boulder.getPosition().getX();
        int move_y = getPosition().getY() - boulder.getPosition().getY();
        Position boulderNewPosition = new Position(boulder.getPosition().getX() + move_x,
                boulder.getPosition().getY() - move_y);
        dungeon.getObjectsAtPosition(boulderNewPosition).stream()
                .forEach(dungeonObject -> dungeonObject.doAccept(boulder));
    }

    @Override
    public void visit(Spider spider) {
        this.currentState.visitSpider(spider);
    }

    @Override
    public void visit(Mercenary mercenary) {
        this.currentState.visitMercenary(mercenary);
    }

    @Override
    public void visit(ZombieToast zombieToast) {
        this.currentState.visitZombieToast(zombieToast);
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
}
