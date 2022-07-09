package dungeonmania.entities.goal;

import java.util.HashMap;
import java.util.Map;

public class GoalFactory {
    private Map<String, Goal> goals = new HashMap<>();

    public GoalFactory() {
        goals.put("exit", new ExitGoal());
        goals.put("treasure", new TreasureGoal());
        goals.put("boulders", new BoulderGoal());
        goals.put("enemies", new EnemyGoal());
    }

    public Goal createGoal(String goalType) {
        return this.goals.get(goalType);
    }
}
