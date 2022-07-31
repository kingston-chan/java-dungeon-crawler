package dungeonmania.behaviours.logicalrules;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.staticobject.floorswitch.ActivatedEntity;

public class CoAndLogic implements LogicRules {

    @Override
    public boolean canActivate(DungeonObject dungeonObject) {
        List<ActivatedEntity> adjActivatedEntities = LogicHelpers
                .getAdjacentActivatedEntities(dungeonObject.getPosition()).stream().filter(ActivatedEntity::isActivated)
                .collect(Collectors.toList());

        return adjActivatedEntities.size() >= 2 && adjActivatedEntities.stream().allMatch(a1 -> adjActivatedEntities
                .stream().allMatch(a2 -> a1.getActivatedTick() == a2.getActivatedTick()));
    }

}
