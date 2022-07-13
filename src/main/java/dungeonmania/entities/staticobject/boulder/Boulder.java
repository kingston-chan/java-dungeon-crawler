package dungeonmania.entities.staticobject.boulder;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.exit.Exit;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.entities.visitor.Visit;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;

public class Boulder extends StaticObject implements Visit {
    @Override
    public void visit(FloorSwitch floorSwitch) {
        this.setPosition(floorSwitch.getPosition());
        floorSwitch.doActivate();
    }

    @Override
    public void visit(Exit exit) {
        this.setPosition(exit.getPosition());
    }

    @Override
    public void visit(Item item) {
        this.setPosition(item.getPosition());
    }

    @Override
    public void visit(Portal portal) {
        this.setPosition(portal.getPosition());
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
