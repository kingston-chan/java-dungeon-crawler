package dungeonmania.factory.actorfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.util.Position;

public class PlayerBuilder implements ActorBuilder {
    @Override
    public void buildActor(Position position, String type) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = new Player();
        player.setUniqueId(UUID.randomUUID().toString());
        player.setPosition(position);
        player.setType(type);
        player.setAttackPoints(dungeon.getConfig("player_attack"));
        player.setHealthPoints(dungeon.getConfig("player_health"));
        dungeon.addDungeonObject(player.getUniqueId(), player);
    }
}
