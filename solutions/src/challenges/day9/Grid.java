package challenges.day9;

import java.util.HashSet;
import java.util.Set;

public class Grid {
    private static final String RIGHT = "R";
    private static final String LEFT = "L";
    private static final String DOWN = "D";
    private static final String UP = "U";
    private final Set<Position> visitedByHPositions = new HashSet<>();
    private Position currentPos;
    private Position hPosition;
    private Position tPosition;

    public Grid() {
        currentPos = new Position(0, 0);
        hPosition = currentPos;
        visitedByHPositions.add(currentPos);
    }

    public void move(int times, String command) {
        if (command.equals(RIGHT)) {
            move(times, 1, 0);
        } else if (command.equals(LEFT)) {
            move(times, -1, 0);
        } else if (command.equals(UP)) {
            move(times, 0, 1);
        } else if (command.equals(DOWN)) {
            move(times, 0, -1);
        } else {
            throw new IllegalStateException();
        }
    }

    private void move(int times, int x, int y) {
        for (int i = 0; i < times; i++) {
            hPosition = new Position(hPosition.getX() + x, hPosition.getY() + y);
            if (isTInHRange()) {
                continue;
            }
            if (hPosition.getY() == tPosition.getY()) {
                tPosition = new Position(tPosition.getX() + x, tPosition.getY() + y);
            } else if (hPosition.getY() > tPosition.getY()) {
                tPosition = new Position(hPosition.getX() - 1, hPosition.getY());
            } else {
                tPosition = new Position(hPosition.getX() + 1, hPosition.getY());
            }
            visitedByHPositions.add(tPosition);
        }
    }

    public boolean isTInHRange() {
        return Math.abs(hPosition.getX() - tPosition.getX()) <= 1 && Math.abs(hPosition.getY() - tPosition.getY()) <= 1;
    }
}
