package dungeonmania.entities.goal;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.zombietoastspawner.ZombieToastSpawner;

public class EnemyGoal implements Goal {

    @Override
    public boolean hasAchieved() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();
        return player.getEnemiesDefeated() >= dungeon.getIntConfig("enemy_goal")
                && dungeon.getStaticObjects().stream().filter(o -> o instanceof ZombieToastSpawner).count() == 0;
    }

    @Override
    public String toString() {
        return this.hasAchieved() ? "" : ":enemies";
    }
}
