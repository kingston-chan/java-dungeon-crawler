package dungeonmania.entities.staticobject.portal;

import javax.management.loading.PrivateClassLoader;
import javax.sound.sampled.Port;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.Actor;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.wall.Wall;
import dungeonmania.util.Position;
import javassist.expr.Instanceof;



public class Portal extends StaticObject {
    private String colour;

    @Override
    public boolean isInteractable() {
        return false;
    }

    public boolean canAccept(Player player) {
        return true;
    }

    public boolean canAccept(NonPlayableActor enemy) {
        return true;
    }

    
    public void doAccept(Player player) {
        player.visit(this);
    }

    //not sure about this
    public boolean doTeleport(Actor actor) {
        return true;
    }

    public Position getDestination() {
        Dungeon dungeon = DungeonManiaController.getDungeon();

        List<Position> adjacentPositions = this.getDestinationPortal().getPosition().getAdjacentPositions();
            
            for (Position position : adjacentPositions) {

                List<DungeonObject> walls = dungeon.getObjectsAtPosition(position).stream().filter(o -> o instanceof Wall).collect(Collectors.toList());
                List<DungeonObject> boulders = dungeon.getObjectsAtPosition(position).stream().filter(o -> o instanceof Boulder).collect(Collectors.toList());

                if (walls.size() == 0 && boulders.size() == 0) {
                    return position;
                }

            }
        
        //no where to go = stay here
        return null;
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

    private Portal getDestinationPortal() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        
        List<Portal> portals = dungeon.getStaticObjects().stream().filter(o ->  o instanceof Portal).map(e -> (Portal) e).collect(Collectors.toList());
        for (Portal portal : portals) {
            if (this.isDestination(portal)) {
                return portal;
            }
        }
        return null;
    }
}
