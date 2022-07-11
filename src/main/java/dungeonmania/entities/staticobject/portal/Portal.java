package dungeonmania.entities.staticobject.portal;

import javax.management.loading.PrivateClassLoader;

import dungeonmania.entities.staticobject.StaticObject;

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
        //depends on entity
        return true;
    }

    public boolean doTeleport(Actor actor) {
        return true;
    }

    public boolean isDestination(Portal portal) {
        return true;
    }

    public String getColour() {
        return this.colour;
    }
    /* I thought we needed this method, but uml says no
    public boolean accept(Boulder boulder) {
        //must check surroundings 
        return true;
    }*/

}
