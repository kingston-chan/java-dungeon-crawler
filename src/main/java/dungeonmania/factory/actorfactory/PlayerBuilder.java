package dungeonmania.factory.actorfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.util.Position;

public class PlayerBuilder implements ActorBuilder {
    @Override
    public DungeonObject buildActor(Position position, String type, Dungeon dungeon) {
        Player player = new Player();
        player.setUniqueId(UUID.randomUUID().toString());
        player.setPosition(position);
        player.setType(type);
        player.setAttackPoints(dungeon.getConfig("player_attack"));
        player.setHealthPoints(dungeon.getConfig("player_health"));
        player.setDefencePoints(0);
        player.setVisitorBehaviour(null);
        dungeon.addDungeonObject(player.getUniqueId(), player);
        return player;
    }
}
