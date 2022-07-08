package dungeonmania.entities.goal;

public class GoalTreeNode {
    private Goal goal = null;
    private String subGoalType = "";
    private GoalTreeNode leftChild = null;
    private GoalTreeNode rightChild = null;

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public String getSubGoalType() {
        return subGoalType;
    }

    public void setSubGoalType(String subGoalType) {
        this.subGoalType = subGoalType;
    }

    public GoalTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(GoalTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public GoalTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(GoalTreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
