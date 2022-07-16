package dungeonmania.entities.goal;

public class AndGoal extends ComplexGoal {
    @Override
    public boolean hasAchieved() {
        return getSubGoal1().hasAchieved() && getSubGoal2().hasAchieved();
    }

    @Override
    public void addSubgoals(Goal subGoal1, Goal subGoal2) {
        setSubGoal1(subGoal1);
        setSubGoal2(subGoal2);
    }

    @Override
    public String toString() {
        if (!getSubGoal1().toString().isEmpty() && !getSubGoal2().toString().isEmpty()) {
            return "(" + getSubGoal1().toString() + " AND " + getSubGoal2().toString() + ")";
        }
        return getSubGoal1().toString() + getSubGoal2().toString();
    }
}
