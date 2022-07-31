package dungeonmania.entities.staticobject.boulder;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.visitor.Visit;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;

public class Boulder extends StaticObject implements Visit {
    @Override
    public void visit(FloorSwitch floorSwitch) {
        setPosition(floorSwitch.getPosition());
        floorSwitch.boulderActivate();
    }

    @Override
    public void doAccept(Player player) {
        player.visit(this);
    }

    @Override
    public boolean canAccept(Boulder boulder) {
        return false;
    }

    @Override
    public boolean canAccept(NonPlayableActor enemy) {
        return false;
    }

    @Override
    public boolean canAccept(Player player) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        return dungeon.getObjectsAtPosition(BoulderHelper.getBoulderPushedPostion(this, player)).stream()
                .allMatch(o -> o.canAccept(this));
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
}
