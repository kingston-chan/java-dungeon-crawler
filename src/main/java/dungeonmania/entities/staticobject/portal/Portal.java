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

    public static boolean checkIfNoWallBoulder(Position position) {
        Dungeon dungeon = DungeonManiaController.getDungeon();

        List<DungeonObject> walls = dungeon.getObjectsAtPosition(position).stream().filter(o -> o instanceof Wall)
                .collect(Collectors.toList());
        List<DungeonObject> boulders = dungeon.getObjectsAtPosition(position).stream()
                .filter(o -> o instanceof Boulder).collect(Collectors.toList());

        return boulders.isEmpty() && walls.isEmpty();
    }

    public Position getExitPosition(Position visitingFrom) {
        if (getDestinationPortal() == null) {
            return null;
        }
        Position dirVisitingFrom = Position.calculatePositionBetween(this.getPosition(), visitingFrom);

        Position exitPosition = new Position(
                this.getDestinationPortal().getPosition().getX() - dirVisitingFrom.getX(),
                this.getDestinationPortal().getPosition().getY() - dirVisitingFrom.getY());

        if (checkIfNoWallBoulder(exitPosition)) {
            return exitPosition;
        }

        List<Position> adjacentPositions = this.getDestinationPortal().getPosition().getAdjacentCardinalPositions();

        adjacentPositions.remove(exitPosition);

        for (Position position : adjacentPositions) {
            if (checkIfNoWallBoulder(position)) {
                return position;
            }
        }

        return null;
    }

    private Portal getDestinationPortal() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        try {
            return dungeon.getStaticObjects().stream()
                    .filter(o -> o instanceof Portal)
                    .map(p -> (Portal) p)
                    .filter(p -> p.isDestination(this))
                    .findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

    public Position getDestination() {
        return getDestinationPortal().getPosition();
    }

    public boolean isDestination(Portal portal) {
        return this.colour.equals(portal.getColour()) && !this.equals(portal);
    }

    public String getColour() {
        return this.colour;
    }

    @Override
    public boolean canAccept(Player player) {
        return getExitPosition(player.getPosition()) != null;
    }

    @Override
    public boolean canAccept(NonPlayableActor enemy) {
        return enemy.canVisitPortal(this);
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
