package dungeonmania.factory.actorfactory;

import dungeonmania.util.Position;

public interface ActorBuilder {
    public void buildActor(Position position, String type);
}
