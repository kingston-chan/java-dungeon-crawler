package dungeonmania.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static dungeonmania.TestUtils.getValueFromConfigFile;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.Direction;

/**
 * Player tests
 * 
 * @author Kingston Chan
 */
public class PlayerIntegrationBlackBoxTests {
    @Test
    public void testSuccessfullyNotifiesZombieSpawners() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse dres = dmc.newGame("d_zombieSpawnerInteract",
                "c_zombieSpawnRate");

        assertTrue(dres.getEntities().size() == 3);

        dres = dmc.tick(Direction.UP);

        assertTrue(dres.getEntities().size() == 4);
    }

    @Test
    public void playerLosesToZombie() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_playerBattlesZombie",
                "c_playerLosesToZombie");

        DungeonResponse res = dmc.tick(Direction.RIGHT);

        assertFalse(res.getBattles().isEmpty());

        double expectedInitialPlayerHealth = Double
                .parseDouble(getValueFromConfigFile("player_health", "c_playerLosesToZombie"));
        double expectedInitialZombieHealth = Double
                .parseDouble(getValueFromConfigFile("zombie_health", "c_playerLosesToZombie"));

        int expectedPlayerAttack = Integer
                .parseInt(getValueFromConfigFile("player_attack", "c_playerLosesToZombie"));
        int expectedZombieAttack = Integer
                .parseInt(getValueFromConfigFile("zombie_attack", "c_playerLosesToZombie"));

        assertEquals(expectedInitialPlayerHealth, res.getBattles().get(0).getInitialPlayerHealth());
        assertEquals(expectedInitialZombieHealth, res.getBattles().get(0).getInitialEnemyHealth());
        assertEquals("zombie_toast", res.getBattles().get(0).getEnemy());
        assertEquals(1, res.getBattles().get(0).getRounds().size());

        RoundResponse playerLostRound = res.getBattles().get(0).getRounds().get(0);

        assertEquals(-(expectedPlayerAttack / 5.0), playerLostRound.getDeltaEnemyHealth());
        assertEquals(-(expectedZombieAttack / 10.0), playerLostRound.getDeltaCharacterHealth());

        // player should be removed from entity list
        assertEquals(0, TestUtils.countEntityOfType(res, "player"));

        // zombie should live
        assertEquals(1, TestUtils.countEntityOfType(res, "zombie"));
    }

    @Test
    public void playerLosesToSpider() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_playerBattlesSpider",
                "c_playerLosesToSpider");

        res = dmc.tick(Direction.LEFT);

        assertFalse(res.getBattles().isEmpty());

        double expectedInitialPlayerHealth = Double
                .parseDouble(getValueFromConfigFile("player_health", "c_playerLosesToSpider"));
        double expectedInitialSpiderHealth = Double
                .parseDouble(getValueFromConfigFile("spider_health", "c_playerLosesToSpider"));

        int expectedPlayerAttack = Integer
                .parseInt(getValueFromConfigFile("player_attack", "c_playerLosesToSpider"));
        int expectedSpiderAttack = Integer
                .parseInt(getValueFromConfigFile("spider_attack", "c_playerLosesToSpider"));

        assertEquals(expectedInitialPlayerHealth, res.getBattles().get(0).getInitialPlayerHealth());
        assertEquals(expectedInitialSpiderHealth, res.getBattles().get(0).getInitialEnemyHealth());
        assertEquals("spider", res.getBattles().get(0).getEnemy());
        assertEquals(1, res.getBattles().get(0).getRounds().size());

        RoundResponse playerLostRound = res.getBattles().get(0).getRounds().get(0);

        assertEquals(-(expectedPlayerAttack / 5.0), playerLostRound.getDeltaEnemyHealth());
        assertEquals(-(expectedSpiderAttack / 10.0), playerLostRound.getDeltaCharacterHealth());

        // player should be removed from entity list
        assertEquals(0, TestUtils.countEntityOfType(res, "player"));

        // spider should live
        assertEquals(1, TestUtils.countEntityOfType(res, "spider"));
    }

    @Test
    public void playerDefeatsZombie() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_playerBattlesZombie",
                "c_playerDefeatsZombie");

        DungeonResponse res = dmc.tick(Direction.RIGHT);

        assertFalse(res.getBattles().isEmpty());

        double expectedInitialPlayerHealth = Double
                .parseDouble(getValueFromConfigFile("player_health", "c_playerDefeatsZombie"));
        double expectedInitialZombieHealth = Double
                .parseDouble(getValueFromConfigFile("zombie_health", "c_playerDefeatsZombie"));

        int expectedPlayerAttack = Integer
                .parseInt(getValueFromConfigFile("player_attack", "c_playerDefeatsZombie"));
        int expectedZombieAttack = Integer
                .parseInt(getValueFromConfigFile("zombie_attack", "c_playerDefeatsZombie"));

        assertEquals(expectedInitialPlayerHealth, res.getBattles().get(0).getInitialPlayerHealth());
        assertEquals(expectedInitialZombieHealth, res.getBattles().get(0).getInitialEnemyHealth());
        assertEquals("zombie_toast", res.getBattles().get(0).getEnemy());
        assertEquals(1, res.getBattles().get(0).getRounds().size());

        RoundResponse playerWinsRound = res.getBattles().get(0).getRounds().get(0);

        assertEquals(-(expectedPlayerAttack / 5.0), playerWinsRound.getDeltaEnemyHealth());
        assertEquals(-(expectedZombieAttack / 10.0), playerWinsRound.getDeltaCharacterHealth());

        // player should be alive
        assertEquals(1, TestUtils.countEntityOfType(res, "player"));

        // zombie dies
        assertEquals(0, TestUtils.countEntityOfType(res, "zombie"));
    }

    @Test
    public void playerDefeatsSpider() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_playerBattlesSpider",
                "c_playerDefeatsSpider");

        res = dmc.tick(Direction.LEFT);

        assertFalse(res.getBattles().isEmpty());

        double expectedInitialPlayerHealth = Double
                .parseDouble(getValueFromConfigFile("player_health", "c_playerDefeatsSpider"));
        double expectedInitialSpiderHealth = Double
                .parseDouble(getValueFromConfigFile("spider_health", "c_playerDefeatsSpider"));

        int expectedPlayerAttack = Integer
                .parseInt(getValueFromConfigFile("player_attack", "c_playerDefeatsSpider"));
        int expectedSpiderAttack = Integer
                .parseInt(getValueFromConfigFile("spider_attack", "c_playerDefeatsSpider"));

        assertEquals(expectedInitialPlayerHealth, res.getBattles().get(0).getInitialPlayerHealth());
        assertEquals(expectedInitialSpiderHealth, res.getBattles().get(0).getInitialEnemyHealth());
        assertEquals("spider", res.getBattles().get(0).getEnemy());
        assertEquals(1, res.getBattles().get(0).getRounds().size());

        RoundResponse playerWinsRound = res.getBattles().get(0).getRounds().get(0);

        assertEquals(-(expectedPlayerAttack / 5.0), playerWinsRound.getDeltaEnemyHealth());
        assertEquals(-(expectedSpiderAttack / 10.0), playerWinsRound.getDeltaCharacterHealth());

        // player should be alive
        assertEquals(1, TestUtils.countEntityOfType(res, "player"));

        // spider dies
        assertEquals(0, TestUtils.countEntityOfType(res, "spider"));
    }
}
