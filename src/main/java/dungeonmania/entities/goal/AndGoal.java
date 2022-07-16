package dungeonmania.entities.goal;

public class AndGoal extends ComplexGoal {

    public AndGoal(Goal subGoal1, Goal subGoal2) {
        super(subGoal1, subGoal2);
    }

    @Override
    public boolean hasAchieved() {
        return getSubGoal1().hasAchieved() && getSubGoal2().hasAchieved();
    }

    @Override
    public String toString() {
        if (!getSubGoal1().toString().isEmpty() && !getSubGoal2().toString().isEmpty()) {
            return "(" + getSubGoal1().toString() + " AND " + getSubGoal2().toString() + ")";
        }
        return getSubGoal1().toString() + getSubGoal2().toString();
    }
}
