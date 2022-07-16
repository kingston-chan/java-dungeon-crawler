package dungeonmania.entities.goal;

public abstract class ComplexGoal implements Goal {
    private Goal subGoal1;
    private Goal subGoal2;

    public Goal getSubGoal1() {
        return subGoal1;
    }

    public void setSubGoal1(Goal subGoal1) {
        this.subGoal1 = subGoal1;
    }

    public Goal getSubGoal2() {
        return subGoal2;
    }

    public void setSubGoal2(Goal subGoal2) {
        this.subGoal2 = subGoal2;
    }

    public abstract void addSubgoals(Goal subGoal1, Goal subGoal2);
}
