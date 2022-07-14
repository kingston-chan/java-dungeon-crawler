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

    private static ComplexGoal createComplexGoals(String type) {
        switch (type) {
            case "AND":
                return new AndGoal();
            case "OR":
                return new OrGoal();
            default:
                return null;
        }
    }

    public static Goal parseJsonToGoals(JSONObject jsonGoal) {
        String goalStr = jsonGoal.getString("goal");

        Goal singleGoal = createGoals(goalStr);

        if (singleGoal == null) {
            JSONArray subgoals = jsonGoal.getJSONArray("subgoals");
            ComplexGoal complexGoal = createComplexGoals(goalStr);
            complexGoal.addSubgoals(parseJsonToGoals(subgoals.getJSONObject(0)),
                    parseJsonToGoals(subgoals.getJSONObject(1)));
            return complexGoal;
        }

        return singleGoal;
    }
}
