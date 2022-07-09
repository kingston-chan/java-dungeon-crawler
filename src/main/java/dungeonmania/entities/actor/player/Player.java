package dungeonmania.entities.actor.player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.Actor;
import dungeonmania.entities.actor.ally.Ally;
import dungeonmania.entities.actor.enemy.Enemy;
import dungeonmania.entities.actor.player.buildables.BuildableBlueprint;
import dungeonmania.entities.actor.player.buildables.Buildables;
import dungeonmania.entities.actor.player.interactables.InteractBehaviour;
import dungeonmania.entities.actor.player.interactables.Interactables;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.collectables.Key;
import dungeonmania.entities.item.potions.Potion;
import dungeonmania.entities.staticobject.StaticObject;

public class Player extends Actor {
    private Inventory inventory = new Inventory();
    private Queue<Potion> potions = new LinkedList<>();
    private Potion potionConsumed = null;
    private List<Ally> allies = new ArrayList<>();

    private Buildables buildables = new Buildables();
    private Interactables interactables = new Interactables();

    private int enemiesDefeated = 0;

    private int bonusAdditiveAttack = 0;
    private int bonusMultiplicativeAttack = 1;
    private int bonusAdditiveDefence = 0;

    public List<Item> getInventory() {
        return inventory.getInventory();
    }

    public boolean hasInInventory(String itemId) {
        return inventory.hasItem(itemId);
    }

    public boolean use(Item item) {
        return item.playerUse(this);
    }

    public boolean removeTreasures(int numTreasures) {
        return inventory.removeTreasures(numTreasures);
    }

    public Key getKey() {
        return inventory.getKey();
    }

    public void tryPickUpKey(Key key) {
        if (getKey() == null) {
            inventory.addKey(key);
        }
    }

    public void usedKey() {
        inventory.addKey(null);
    }

    public void consumeQueuedPotionEffect() {
        this.potionConsumed = this.potions.poll();

    }

    private void notifyEnemies(Dungeon dungeon) {
        // for (Enemy e : dungeon.getActiveEnemies()) {
        // if (this.potionConsumed == null) {
        // e.update(e.getDefaultAutomatedMovementBehaviour(),
        // e.getDefaultHostBehaviour());
        // } else {
        // e.update(this.potionConsumed.getMovementEffect(),
        // this.potionConsumed.getHostEffect());
        // }
        // }
    }

    private void notifyStaticObjects(Dungeon dungeon) {
        // for (StaticObject s : dungeon.getStaticObjects()) {
        // s.update();
        // }
    }

    private void notifyDungeon(Dungeon dungeon) {
        dungeon.updateSpawnSpider();
    }

    public void notifyAllObservers(Dungeon dungeon) {
        notifyEnemies(dungeon);
        notifyStaticObjects(dungeon);
        notifyDungeon(dungeon);
    }

    public void usePotion(Potion potion, int duration) {
        for (int i = 0; i < duration; i++) {
            this.potions.add(potion);
        }
    }

    /**
     * @precond unique id is interactable
     * @param dungeon
     * @param uniqueId
     * @return
     */
    public boolean interact(Dungeon dungeon, String uniqueId) {
        DungeonObject dungeonObject = dungeon.getDungeonObject(uniqueId);
        InteractBehaviour interaction = interactables.getInteraction(dungeonObject.getType());
        return interaction.interact(dungeon, this, uniqueId);
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
        return bp.isBuildable(this.inventory);
    }

    /**
     * @precond itemType is valid and buildable
     * @param dungeon
     * @param itemType
     */
    public void build(Dungeon dungeon, String itemType) {
        BuildableBlueprint bp = buildables.getBlueprint(itemType);
        bp.buildItem(dungeon, this.inventory);
    }

    public Potion getPotionConsumed() {
        return this.potionConsumed;
    }

    public void addAlly(Ally ally) {
        this.allies.add(ally);
    }

    public List<Ally> getAllies() {
        return this.allies;
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
    public boolean isInteractable() {
        return false;
    }
}
