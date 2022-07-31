package dungeonmania.behaviours.logicalrules;

import dungeonmania.entities.staticobject.floorswitch.ActivatedEntity;

import dungeonmania.entities.DungeonObject;

public class OrLogic implements LogicRules {

    @Override
    public boolean canActivate(DungeonObject dungeonObject) {
        return LogicHelpers.getAdjacentActivatedEntities(dungeonObject.getPosition()).stream()
                .anyMatch(ActivatedEntity::isActivated);
    }

}
