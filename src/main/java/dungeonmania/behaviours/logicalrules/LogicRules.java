package dungeonmania.behaviours.logicalrules;

import java.io.Serializable;

import dungeonmania.entities.DungeonObject;

public interface LogicRules extends Serializable {
    public boolean canActivate(DungeonObject dungeonObject);
}
