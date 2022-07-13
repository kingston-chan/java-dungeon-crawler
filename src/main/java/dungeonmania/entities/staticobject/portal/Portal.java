package dungeonmania.entities.staticobject.portal;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.wall.Wall;
import dungeonmania.util.Position;

public class Portal extends StaticObject {
    private String colour;

    public Portal(String colour) {
        this.colour = colour;
    }

    public Position getDestination() {
        Dungeon dungeon = DungeonManiaController.getDungeon();

        if (this.getDestinationPortal() == null) {
            return null;
        }

        List<Position> adjacentPositions = this.getDestinationPortal().getPosition().getAdjacentCardinalPositions();

        for (Position position : adjacentPositions) {

            List<DungeonObject> walls = dungeon.getObjectsAtPosition(position).stream().filter(o -> o instanceof Wall)
                    .collect(Collectors.toList());
            List<DungeonObject> boulders = dungeon.getObjectsAtPosition(position).stream()
                    .filter(o -> o instanceof Boulder).collect(Collectors.toList());

            if (walls.size() == 0 && boulders.size() == 0) {
                return position;
            }

        }

        // no where to go = stay here
        return null;
    }

    public boolean isDestination(Portal portal) {
        return this.colour.equals(portal.getColour());
    }

    public String getColour() {
        return this.colour;
    }

    private Portal getDestinationPortal() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        List<Portal> portals = dungeon.getStaticObjects().stream()
                .filter(o -> o instanceof Portal).map(o -> (Portal) o)
                .collect(Collectors.toList());
        try {
            return portals.stream()
                    .filter(o -> o.isDestination(this))
                    .findFirst().get();

        } catch (Exception e) {
            // should never happen because all portals should have
            // destination portal, unless destroyed by bomb
            return null;
        }
    }

    @Override
    public boolean canAccept(Player player) {
        return this.getDestination() != null;
    }

    @Override
    public boolean canAccept(NonPlayableActor enemy) {
        if (enemy instanceof Mercenary) {
            return this.getDestination() != null;
        }
        return true;
    }

    @Override
    public void doAccept(Player player) {
        player.visit(this);
    }

    @Override
    public void doAccept(NonPlayableActor entity) {
        entity.visit(this);
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
}
