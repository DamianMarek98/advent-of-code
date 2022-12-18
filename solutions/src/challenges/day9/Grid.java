package challenges.day9;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grid {
    private static final String RIGHT = "R";
    private static final String LEFT = "L";
    private static final String DOWN = "D";
    private static final String UP = "U";
    private final Set<Position> visitedByTPositions = new HashSet<>();
    private Position hPosition;
    private Position tPosition;

    public Grid() {
        hPosition = new Position(0, 0);
        tPosition = hPosition;
        visitedByTPositions.add(hPosition);
    }

    public void move(int times, String command) {
        switch (command) {
            case RIGHT -> move(times, 1, 0);
            case LEFT -> move(times, -1, 0);
            case UP -> move(times, 0, 1);
            case DOWN -> move(times, 0, -1);
            default -> throw new IllegalStateException();
        }
    }

    private void move(int times, int x, int y) {
        for (int i = 0; i < times; i++) {
            hPosition = new Position(hPosition.x() + x, hPosition.y() + y);
            if (isTInHRange()) {
                continue;
            }
            if (hPosition.y() == tPosition.y() || hPosition.x() == tPosition.x()) {
                tPosition = new Position(tPosition.x() + x, tPosition.y() + y);
            } else {
                tPosition = findTPosition();
            }
            visitedByTPositions.add(tPosition);
        }
    }

    private boolean isTInHRange() {
        return Math.abs(hPosition.x() - tPosition.x()) <= 1 && Math.abs(hPosition.y() - tPosition.y()) <= 1;
    }

    private Position findTPosition() {
        var possiblePositions = List.of(new Position(hPosition.x() + 1, hPosition.y()),
                new Position(hPosition.x() - 1, hPosition.y()),
                new Position(hPosition.x(), hPosition.y() + 1),
                new Position(hPosition.x(), hPosition.y() - 1));
        return possiblePositions.stream().filter(pos -> pos.x() != tPosition.x() && pos.y() != tPosition.y())
                .min(Comparator.comparingInt(this::calculateDistanceFromT))
                .orElseThrow(IllegalAccessError::new);
    }

    private int calculateDistanceFromT(Position pos) {
        return Math.abs(pos.x() - tPosition.x()) + Math.abs(pos.y() - tPosition.y());
    }

    public int getNumberOfTVisitedPositions() {
        return visitedByTPositions.size();
    }
}
