package dungeonmania.entities.goal;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.exit.Exit;

public class ExitGoal implements Goal {
    @Override
    public boolean hasAchieved(StringBuilder allGoals) {
        allGoals.append(":exit");
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();
        return dungeon.getDungeonObjects().stream()
                .filter(dungeonObject -> dungeonObject instanceof Exit)
                .anyMatch(dungeonObject -> dungeonObject.getPosition().equals(player.getPosition()));
    }
}
