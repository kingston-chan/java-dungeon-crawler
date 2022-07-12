package dungeonmania.behaviours.automatedmovement;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.util.Position;

public class MoveRandomly implements MovementBehaviour {

    // actor should be able to move randomly (cardinal and diagonal) by one space

    @Override
    public void move(NonPlayableActor npa) { 
        int x = (int) Math.random() * (1 - -1);
        int y = (int) Math.random() * (1 - -1);

        npa.setPosition(npa.getPosition().translateBy(x, y));
    }

}
