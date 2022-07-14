package dungeonmania.entities.goal;

public abstract class ComplexGoal implements Goal {
    public abstract void addSubgoals(Goal subGoal1, Goal subGoal2);
}
