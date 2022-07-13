package dungeonmania.behaviours.automatedmovement;

import java.util.ArrayList;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.util.Position;

public class FollowPlayer implements MovementBehaviour {

    @Override
    public void move(NonPlayableActor npa) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();

        // Position playerPrevPos = player.getPrevPosition();
        // npa.setPosition(playerPrevPos);

    }

}
