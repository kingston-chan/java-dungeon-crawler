package dungeonmania.behaviours.logicalrules;

import java.util.List;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.staticobject.floorswitch.ActivatedEntity;

public class XorLogic implements LogicRules {

    @Override
    public boolean canActivate(DungeonObject dungeonObject) {
        List<ActivatedEntity> adjActivateEntities = LogicHelpers
                .getAdjacentActivatedEntities(dungeonObject.getPosition());

        // if (dungeonObject instanceof ActivatedEntity) {
        // return adjActivateEntities.size() == 1
        // && adjActivateEntities.stream().allMatch(ActivatedEntity::isActivated);
        // }

        return adjActivateEntities.stream().filter(ActivatedEntity::isActivated).count() == 1;
    }

}
