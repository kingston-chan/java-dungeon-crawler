package dungeonmania.factory.actorfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.factory.FactoryHelpers;

public class PlayerBuilder implements ActorBuilder {

    @Override
    public void buildActor(JSONObject actor) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = new Player();
        player.setUniqueId(UUID.randomUUID().toString());
        player.setPosition(FactoryHelpers.extractPosition(actor));
        player.setType(FactoryHelpers.extractType(actor));
        player.setAttackPoints(dungeon.getIntConfig("player_attack"));
        player.setHealthPoints(dungeon.getIntConfig("player_health"));
        player.setPreviousPosition(player.getPosition());
        dungeon.setPlayer(player);
        dungeon.addDungeonObject(player.getUniqueId(), player);
    }
}
