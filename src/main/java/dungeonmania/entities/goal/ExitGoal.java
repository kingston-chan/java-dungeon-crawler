package dungeonmania.entities.goal;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.exit.Exit;

public class ExitGoal implements Goal {

    @Override
    public boolean hasAchieved() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();
        return dungeon.getStaticObjects().stream().filter(o -> o instanceof Exit)
                .anyMatch(o -> o.getPosition().equals(player.getPosition()));
    }

    @Override
    public String toString() {
        return ":exit";
    }
}
