package dungeonmania.entities.goal;

public abstract class ComplexGoal implements Goal {
    private Goal subGoal1;
    private Goal subGoal2;

    public ComplexGoal(Goal subGoal1, Goal subGoal2) {
        this.subGoal1 = subGoal1;
        this.subGoal2 = subGoal2;
    }

    public Goal getSubGoal1() {
        return subGoal1;
    }

    public Goal getSubGoal2() {
        return subGoal2;
    }

}
