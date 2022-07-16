package dungeonmania.entities.goal;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoalFactory {
    private static Goal createGoals(String type) {
        switch (type) {
            case "enemies":
                return new EnemyGoal();
            case "boulders":
                return new BoulderGoal();
            case "treasure":
                return new TreasureGoal();
            case "exit":
                return new ExitGoal();
            default:
                return null;
        }
    }

    private static ComplexGoal createComplexGoals(String type, Goal subGoal1, Goal subGoal2) {
        switch (type) {
            case "AND":
                return new AndGoal(subGoal1, subGoal2);
            case "OR":
                return new OrGoal(subGoal1, subGoal2);
            default:
                return null;
        }
    }

    public static Goal parseJsonToGoals(JSONObject jsonGoal) {
        String goalStr = jsonGoal.getString("goal");

        Goal singleGoal = createGoals(goalStr);

        if (singleGoal == null) {
            JSONArray subgoals = jsonGoal.getJSONArray("subgoals");
            Goal subgoal1 = parseJsonToGoals(subgoals.getJSONObject(0));
            Goal subgoal2 = parseJsonToGoals(subgoals.getJSONObject(1));
            ComplexGoal complexGoal = createComplexGoals(goalStr, subgoal1, subgoal2);
            return complexGoal;
        }

        return singleGoal;
    }
}
