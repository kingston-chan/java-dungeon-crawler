package dungeonmania.entities.goal;

public class AndGoal extends ComplexGoal {
    private Goal subGoal1;
    private Goal subGoal2;

    @Override
    public boolean hasAchieved() {
        return this.subGoal1.hasAchieved() && this.subGoal2.hasAchieved();
    }

    @Override
    public void addSubgoals(Goal subGoal1, Goal subGoal2) {
        this.subGoal1 = subGoal1;
        this.subGoal2 = subGoal2;
    }

    @Override
    public String toString() {
        if (!this.subGoal1.toString().isEmpty() && !this.subGoal2.toString().isEmpty()) {
            return "(" + this.subGoal1.toString() + " AND " + this.subGoal2.toString() + ")";
        }
        return this.subGoal1.toString() + this.subGoal2.toString();
    }
}
