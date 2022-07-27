package dungeonmania.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

import org.json.JSONObject;

import dungeonmania.factory.actorfactory.PlayerBuilder;
import dungeonmania.factory.staticobjectfactory.ExitBuilder;
import dungeonmania.factory.staticobjectfactory.WallBuilder;
import dungeonmania.util.Position;

public class Maze {
    private List<Position> getCardinalAdjacentDistanceTwo(Position currPos) {
        List<Position> adjacentCardinalPositionsDistTwo = new ArrayList<>();
        int x = currPos.getX();
        int y = currPos.getY();
        adjacentCardinalPositionsDistTwo.add(new Position(x, y - 2));
        adjacentCardinalPositionsDistTwo.add(new Position(x + 2, y));
        adjacentCardinalPositionsDistTwo.add(new Position(x, y + 2));
        adjacentCardinalPositionsDistTwo.add(new Position(x - 2, y));
        return adjacentCardinalPositionsDistTwo;
    }

    private boolean isWithinBoundary(int minX, int maxX, int minY, int maxY, Position currPos) {
        return (currPos.getX() > minX && currPos.getX() < maxX) && (currPos.getY() > minY && currPos.getY() < maxY);
    }

    private List<Position> getWallNeighbours(int minX, int maxX, int minY, int maxY, List<Position> allPositions,
            Position currPos) {
        List<Position> adjPosDistTwo = getCardinalAdjacentDistanceTwo(currPos);
        return adjPosDistTwo.stream()
                .filter(p1 -> allPositions.contains(p1))
                .filter(p1 -> isWithinBoundary(minX, maxX, minY, maxY, p1))
                .collect(Collectors.toList());

    }

    private List<Position> getEmptyNeighbours(int minX, int maxX, int minY, int maxY, List<Position> allPositions,
            Position currPos) {
        List<Position> adjPosDistTwo = getCardinalAdjacentDistanceTwo(currPos);
        return adjPosDistTwo.stream()
                .filter(p1 -> !allPositions.contains(p1))
                .filter(p1 -> isWithinBoundary(minX, maxX, minY, maxY, p1))
                .collect(Collectors.toList());
    }

    private Boolean isBetween(int bound1, int bound2, int toBeChecked) {
        return (bound1 < toBeChecked && toBeChecked < bound2) || (bound1 > toBeChecked && toBeChecked > bound2);
    }

    private Position getPositionBetween(Position p1, Position p2) {
        if ((p1.getX() - p2.getX()) == 0) {
            return p1.getAdjacentCardinalPositions().stream()
                    .filter(p -> p.getX() == p1.getX())
                    .filter(p -> isBetween(p1.getY(), p2.getY(), p.getY()))
                    .findFirst().get();
        } else {
            return p1.getAdjacentCardinalPositions().stream()
                    .filter(p -> p.getY() == p1.getY())
                    .filter(p -> isBetween(p1.getX(), p2.getX(), p.getX()))
                    .findFirst().get();
        }
    }

    public void createNewRandomMaze(int xStart, int yStart, int xEnd, int yEnd) {
        int mazeStartX = xStart - 1;
        int mazeStartY = yStart - 1;
        int mazeEndX = xEnd + 1;
        int mazeEndY = yEnd + 1;

        List<Position> allPositionsInMaze = new ArrayList<>();

        for (int y = mazeStartY; y <= mazeEndY; y++) {
            for (int x = mazeStartX; x <= mazeEndX; x++) {
                allPositionsInMaze.add(new Position(x, y));
            }
        }

        Position startPos = new Position(xStart, yStart);

        allPositionsInMaze.remove(startPos);

        Queue<Position> options = new LinkedList<>(
                getWallNeighbours(mazeStartX, mazeEndX, mazeStartY, mazeEndY, allPositionsInMaze, startPos));

        while (!options.isEmpty()) {
            Position next = options.poll();

            List<Position> emtpyNeighbours = getEmptyNeighbours(mazeStartX, mazeEndX, mazeStartY, mazeEndY,
                    allPositionsInMaze, next);

            if (!emtpyNeighbours.isEmpty()) {
                Random rng = new Random();
                Position distTwoNeighbour = emtpyNeighbours.get(rng.nextInt(emtpyNeighbours.size()));
                Position positionBetween = getPositionBetween(next, distTwoNeighbour);
                allPositionsInMaze.remove(distTwoNeighbour);
                allPositionsInMaze.remove(positionBetween);
                allPositionsInMaze.remove(next);
            }

            getWallNeighbours(mazeStartX, mazeEndX, mazeStartY, mazeEndY,
                    allPositionsInMaze, next).forEach(p -> {
                        if (!options.contains(p)) {
                            options.add(p);
                        }
                    });
        }

        Position exit = new Position(xEnd, yEnd);

        if (allPositionsInMaze.contains(exit)) {
            allPositionsInMaze.remove(exit);

            List<Position> neighbourDistOne = exit.getAdjacentCardinalPositions().stream()
                    .filter(p -> isWithinBoundary(mazeStartX, mazeEndX, mazeStartY, mazeEndY, p))
                    .collect(Collectors.toList());

            if (neighbourDistOne.stream().allMatch(p -> allPositionsInMaze.contains(p))) {
                Random rng = new Random();
                Position neighbour = neighbourDistOne.get(rng.nextInt(neighbourDistOne.size()));
                allPositionsInMaze.remove(neighbour);
            }
        }

        WallBuilder wallBuilder = new WallBuilder();

        allPositionsInMaze.forEach(p -> {
            JSONObject wall = new JSONObject();
            wall.put("type", "wall");
            wall.put("x", p.getX());
            wall.put("y", p.getY());
            wallBuilder.buildStaticObject(wall);
        });

        PlayerBuilder playerBuilder = new PlayerBuilder();
        JSONObject player = new JSONObject();
        player.put("type", "player");
        player.put("x", xStart);
        player.put("y", yStart);
        playerBuilder.buildActor(player);

        ExitBuilder exitBuilder = new ExitBuilder();
        JSONObject exitJObject = new JSONObject();
        exitJObject.put("type", "exit");
        exitJObject.put("x", xEnd);
        exitJObject.put("y", yEnd);
        exitBuilder.buildStaticObject(exitJObject);
    }
}
