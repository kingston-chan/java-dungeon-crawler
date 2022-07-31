package dungeonmania.behaviours.logicalrules;

import java.util.List;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.staticobject.floorswitch.ActivatedEntity;
import dungeonmania.entities.staticobject.logicentities.LogicFloorSwitch;
import dungeonmania.entities.staticobject.wire.Wire;

public class XorLogic implements LogicRules {

    @Override
    public boolean canActivate(DungeonObject dungeonObject) {
        List<ActivatedEntity> adjActivateEntities = LogicHelpers
                .getAdjacentActivatedEntities(dungeonObject.getPosition());

        if (dungeonObject instanceof ActivatedEntity) {
            return adjActivateEntities.stream().filter(o -> o instanceof LogicFloorSwitch || o instanceof Wire)
                    .count() < 2
                    && adjActivateEntities.stream().filter(ActivatedEntity::isActivated).count() == 1;
        }

        return adjActivateEntities.stream().filter(ActivatedEntity::isActivated).count() == 1;
    }

}
