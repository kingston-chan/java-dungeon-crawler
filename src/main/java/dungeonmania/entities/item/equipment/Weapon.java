package dungeonmania.entities.item.equipment;

public abstract class Weapon extends Equipment {
    private int attack;

    public Weapon(int attack, int durability) {
        super(durability);
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }
}
