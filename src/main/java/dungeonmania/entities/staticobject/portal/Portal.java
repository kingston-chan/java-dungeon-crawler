package dungeonmania.entities.staticobject.portal;

import javax.management.loading.PrivateClassLoader;

import dungeonmania.entities.actor.Actor;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.util.Position;

public class Portal extends StaticObject {
    private String colour;

    @Override
    public boolean isInteractable() {
        return false;
    }

    public boolean accept(Player player) {
        return true;
    }

    public boolean accept(NonPlayableActor enemy) {
        if (enemy instanceof Mercenary) {
            return true;
        }
        return false;
    }

    //not sure about this
    public boolean doTeleport(Actor actor) {
        return true;
    }

    public Position getDestination() {
        //return the position of where destination portal teleports the mercenary/player
        return this.getPosition();
    }
    public boolean isDestination(Portal portal) {
        if (portal.getColour().equals(this.colour)) {
            return true;
        }
        return false;
    }

    public String getColour() {
        return this.colour;
    }
}
