package dungeonmania.factory.staticobjectfactory;

import dungeonmania.util.Position;

public interface StaticObjectBuilder {
    public void buildStaticObject(Position position, String type, String portalColour, int key);
}
