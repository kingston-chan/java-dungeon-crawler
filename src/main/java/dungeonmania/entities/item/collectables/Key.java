package dungeonmania.entities.item.collectables;

public class Key extends Collectable {
    private int keyNum;

    public Key(int keyNum) {
        this.keyNum = keyNum;
    }

    public int getKeyNum() {
        return this.keyNum;
    }
}
