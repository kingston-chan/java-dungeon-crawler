package dungeonmania.factory.itemfactory;

import dungeonmania.util.Position;

public interface ItemBuilder {
    public void buildItem(Position position, String type, int keyNum);
}
