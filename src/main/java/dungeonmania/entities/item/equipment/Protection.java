package dungeonmania.entities.item.equipment;

public abstract class Protection extends Equipment {
    private int defence;

    public Protection(int defence, int durability){
        super(durability);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }
}
