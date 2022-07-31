package dungeonmania.entities.actor.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.Actor;
import dungeonmania.entities.actor.nonplayableactor.Hydra;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.player.buildables.BuildableBlueprint;
import dungeonmania.entities.actor.player.buildables.Buildables;
import dungeonmania.entities.actor.player.interactables.InteractBehaviour;
import dungeonmania.entities.actor.player.interactables.Interactables;
import dungeonmania.entities.actor.player.states.InvinicibleState;
import dungeonmania.entities.actor.player.states.InvisibleState;
import dungeonmania.entities.actor.player.states.NormalState;
import dungeonmania.entities.actor.player.states.PlayerState;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.Key;
import dungeonmania.entities.item.potions.Potion;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.boulder.BoulderHelper;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.entities.staticobject.zombietoastspawner.ZombieToastSpawner;
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
        Dungeon dungeon = DungeonManiaController.getDungeon();

        if (getKey() == null) {
            addToInventory(key);
            dungeon.removeDungeonObject(key.getUniqueId());
        }
    }

    public void consumeQueuedPotionEffect() {
        this.potionConsumed = this.potions.poll();
        if (potionConsumed == null) {
            this.currentState = this.normalState;
            return;
        }
        this.potionConsumed.consumedBy(this);
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
    public boolean interact(String uniqueId) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        DungeonObject dungeonObject = dungeon.getDungeonObject(uniqueId);
        InteractBehaviour interaction = interactables.getInteraction(dungeonObject.getType());
        return interaction.interact(this, uniqueId);
    }

    public boolean canBuild(String itemName) {
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
        this.bonusMultiplicativeAttack *= attackPoints;
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

    public PlayerState getCurrentPlayerState() {
        return this.currentState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.currentState = playerState;
    }

    public PlayerState getNormalState() {
        return this.normalState;
    }

    public PlayerState getInvincibleState() {
        return this.invinicibleState;
    }

    public PlayerState getInvisibleState() {
        return this.invisibleState;
    }

    public void setPreviousPosition(Position position) {
        this.previousPosition = position;
    }

    public Position getPreviousPosition() {
        return this.previousPosition;
    }

    public double attackedBy(NonPlayableActor npa) {
        Dungeon dungeon = DungeonManiaController.getDungeon();

        int allyDefence = dungeon.getIntConfig("ally_defence");

        int totalBonusDefence = getBonusAdditiveDefence() + (getNumAllies() * allyDefence);

        double npaDamage = (npa.getAttackPoints() - totalBonusDefence) / 10.0;

        npaDamage = npaDamage < 0 ? 0 : npaDamage;

        takeDamage(npaDamage);

        return -npaDamage;
    }

    @Override
    public void doAccept(NonPlayableActor npa) {
        this.currentState.acceptNonPlayableActor(npa);
    }

    @Override
    public void visit(Item item) {
        addToInventory(item);
        DungeonManiaController.getDungeon().removeDungeonObject(item.getUniqueId());
    }

    @Override
    public void visit(Portal portal) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Position destinationPortalPosition = portal.getDestination();
        Position exitPosition = portal.getExitPosition(getPosition());
        setPreviousPosition(getPosition());
        setPosition(destinationPortalPosition);
        dungeon.getStaticObjectsAtPosition(exitPosition).stream()
                .forEach(o -> o.doAccept(this));
        if (destinationPortalPosition == getPosition()) {
            dungeon.getNonPlayableActorsAtPosition(exitPosition).stream()
                    .forEach(o -> o.doAccept(this));
            dungeon.getItems().stream().filter(i -> i.getPosition().equals(exitPosition))
                    .forEach(o -> o.doAccept(this));
            setPosition(exitPosition);
        }
    }

    @Override
    public void visit(Boulder boulder) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Position oldBoulderPos = boulder.getPosition();
        // deactivated switches on old boulder pos
        dungeon.getObjectsAtPosition(oldBoulderPos).stream().filter(o -> o instanceof FloorSwitch)
                .forEach(o -> ((FloorSwitch) o).playerDeactivate());
        dungeon.getObjectsAtPosition(BoulderHelper.getBoulderPushedPostion(boulder, this)).stream()
                .forEach(dungeonObject -> dungeonObject.doAccept(boulder));
        if (boulder.getPosition() == oldBoulderPos) {
            boulder.setPosition(BoulderHelper.getBoulderPushedPostion(boulder, this));
        }
    }

    @Override
    public void visit(Spider spider) {
        this.currentState.visitSpider(spider);
    }

    @Override
    public void visit(Hydra hydra) {
        this.currentState.visitHydra(hydra);
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

    public void reduceAlly() {
        this.numAllies--;
    }
}
