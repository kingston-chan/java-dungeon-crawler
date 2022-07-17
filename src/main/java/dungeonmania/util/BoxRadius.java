package dungeonmania.util;

import java.util.ArrayList;
import java.util.List;

public class BoxRadius {
    public static List<Position> getBoxRadiusPositions(int radius, Position startingPosition) {
        List<Position> positions = new ArrayList<>();
        int start_x = startingPosition.getX() - radius;
        int start_y = startingPosition.getY() - radius;

        for (int i = start_y; i < start_y + (radius * 2 + 1); i++) {
            for (int j = start_x; j < (radius * 2 + 1) + start_x; j++) {
                positions.add(new Position(j, i));
            }
        }

        return positions;
    }
}
