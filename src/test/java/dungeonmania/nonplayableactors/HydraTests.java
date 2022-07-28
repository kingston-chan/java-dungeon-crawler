package dungeonmania.nonplayableactors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.Direction;

public class HydraTests {
	@Test
	public void testHydraBattlePlayerInitiates() {
		DungeonManiaController dmc = new DungeonManiaController();
		dmc.newGame("d_hydraTest", "c_hydraTest");
		long seed = (System.currentTimeMillis() / 100) * 100;
		Random rng = new Random(seed);
		DungeonResponse dres = dmc.tick(Direction.RIGHT);
		BattleResponse hydraBattle = dres.getBattles().get(0);

		double hydraHealAmount = Integer
				.parseInt(TestUtils.getValueFromConfigFile("hydra_health_increase_amount",
						"c_hydraTest"))
				* 1.0;

		double hydraHealRate = Double
				.parseDouble(TestUtils.getValueFromConfigFile("hydra_health_increase_rate",
						"c_hydraTest"));

		double playerDamage = Integer
				.parseInt(TestUtils.getValueFromConfigFile("player_attack", "c_hydraTest")) / 5.0;

		for (RoundResponse r : hydraBattle.getRounds()) {
			double hydraDeltaHealth = rng.nextDouble() < hydraHealRate ? hydraHealAmount : -(playerDamage);
			assertEquals(hydraDeltaHealth, r.getDeltaEnemyHealth());
		}
	}

	@Test
	public void testHydraBattleHydraInitiates() {
		DungeonManiaController dmc = new DungeonManiaController();
		dmc.newGame("d_hydraInitiatesBattle", "c_hydraTest");

		long seed = (System.currentTimeMillis() / 100) * 100;
		Random rng = new Random(seed);

		DungeonResponse dres = dmc.tick(Direction.UP);

		BattleResponse hydraBattle = dres.getBattles().get(0);
		double hydraHealAmount = Integer
				.parseInt(TestUtils.getValueFromConfigFile("hydra_health_increase_amount",
						"c_hydraTest"))
				* 1.0;

		double hydraHealRate = Double
				.parseDouble(TestUtils.getValueFromConfigFile("hydra_health_increase_rate",
						"c_hydraTest"));

		double playerDamage = Integer
				.parseInt(TestUtils.getValueFromConfigFile("player_attack", "c_hydraTest")) / 5.0;

		for (RoundResponse r : hydraBattle.getRounds()) {
			double hydraDeltaHealth = rng.nextDouble() < hydraHealRate ? hydraHealAmount : -(playerDamage);
			assertEquals(hydraDeltaHealth, r.getDeltaEnemyHealth());
		}
	}

	@Test
	public void testHydraAlwaysRegenerates() {
		DungeonManiaController dmc = new DungeonManiaController();
		dmc.newGame("d_hydraInitiatesBattle", "c_hydraAlwaysHeals");
		DungeonResponse dres = dmc.tick(Direction.UP);
		BattleResponse hydraBattle = dres.getBattles().get(0);
		double hydraHealAmount = Integer
				.parseInt(TestUtils.getValueFromConfigFile("hydra_health_increase_amount",
						"c_hydraTest"))
				* 1.0;

		for (RoundResponse r : hydraBattle.getRounds()) {
			assertEquals(hydraHealAmount, r.getDeltaEnemyHealth());
		}
	}

	@Test
	public void testHydraNeverRegenerates() {
		DungeonManiaController dmc = new DungeonManiaController();
		dmc.newGame("d_hydraInitiatesBattle", "c_hydraNeverHeals");
		DungeonResponse dres = dmc.tick(Direction.UP);
		BattleResponse hydraBattle = dres.getBattles().get(0);

		double playerDamage = Integer
				.parseInt(TestUtils.getValueFromConfigFile("player_attack", "c_hydraNeverHeals")) / 5.0;

		for (RoundResponse r : hydraBattle.getRounds()) {
			assertEquals(-(playerDamage), r.getDeltaEnemyHealth());
		}
	}
}
