package dungeonmania.entities.staticobject.zombietoastspawner;

import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.wall.Wall;
import dungeonmania.factory.actorfactory.ZombieToastBuilder;
import dungeonmania.util.Position;

public class ZombieToastSpawner extends StaticObject {
    private int tickCounter = 0;

    private Position findOpenPosition() {
        Dungeon dungeon = DungeonManiaController.getDungeon();

        try {
            return getPosition().getAdjacentCardinalPositions().stream()
                    .filter(p -> !dungeon.getStaticObjectsAtPosition(p)
                            .stream().anyMatch(o -> o instanceof Wall || o instanceof Boulder))
                    .findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

    public void updateSpawnRate() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        int spawnrate = dungeon.getIntConfig("zombie_spawn_rate");

        if (spawnrate == 0) {
            return;
        }

        tickCounter++;

        if (tickCounter % spawnrate == 0) {
            // check cardional directions
            Position spawnPosition = findOpenPosition();
            if (spawnPosition != null) {
                ZombieToastBuilder builder = new ZombieToastBuilder();
                ZombieToast spawnedZombie = builder.buildZombie(spawnPosition);
                dungeon.getObjectsAtPosition(spawnPosition).forEach(o -> o.doAccept(spawnedZombie));
            }
        }

    }

    @Override
    public boolean canAccept(Boulder boulder) {
        return true;
    }

    @Override
    public boolean isInteractable() {
        return true;
    }
}
