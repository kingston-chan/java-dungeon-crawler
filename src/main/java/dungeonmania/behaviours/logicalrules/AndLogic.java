package dungeonmania.behaviours.logicalrules;

import java.util.List;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.staticobject.floorswitch.ActivatedEntity;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;

public class AndLogic implements LogicRules {

	@Override
	public boolean canActivate(DungeonObject dungeonObject) {
		List<ActivatedEntity> adjActivatedEntities = LogicHelpers
				.getAdjacentActivatedEntities(dungeonObject.getPosition());

		return (adjActivatedEntities.stream().filter(e -> e instanceof FloorSwitch).count() >= 2
				&& adjActivatedEntities.stream().filter(e -> e instanceof FloorSwitch)
						.allMatch(e -> e.isActivated()))
				|| (adjActivatedEntities.stream().filter(ActivatedEntity::isActivated).count() >= 2
						&& adjActivatedEntities.stream().filter(e -> e instanceof FloorSwitch)
								.count() <= 1);
	}

}
