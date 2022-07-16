package dungeonmania.entities.staticobject.zombietoastspawner;

import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.factory.actorfactory.ZombieToastBuilder;
import dungeonmania.util.Position;

public class ZombieToastSpawner extends StaticObject {
    private int tickCounter = 0;

    public void updateSpawnRate() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        int spawnrate = dungeon.getConfig("zombie_spawn_rate");

        if (spawnrate == 0) {
            return;
        }

        tickCounter += 1;
        if (tickCounter % spawnrate == 0) {
            // check cardional directions
            List<Position> adjacentCardinalPositions = this.getPosition().getAdjacentCardinalPositions();

            for (Position position : adjacentCardinalPositions) {

                if (dungeon.getObjectsAtPosition(position).size() == 0) {
                    ZombieToastBuilder builder = new ZombieToastBuilder();
                    builder.buildActor(position, "zombie_toast");
                    return;
                }
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
