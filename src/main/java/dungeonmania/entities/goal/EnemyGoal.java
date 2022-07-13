package dungeonmania.entities.goal;

public class EnemyGoal implements Goal {

    @Override
    public boolean hasAchieved() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String toString() {
        return this.hasAchieved() ? "" : ":enemies";
    }
}
