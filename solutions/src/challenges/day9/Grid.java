package challenges.day9;

import java.util.*;
import java.util.stream.Stream;

public class Grid {
    private static final String RIGHT = "R";
    private static final String LEFT = "L";
    private static final String DOWN = "D";
    private static final String UP = "U";
    private final Set<Position> visitedByTPositions = new HashSet<>();
    //private Position hPosition;
    //private Position tPosition;
    private final List<Position> knots;

    public Grid() {
        knots = Arrays.asList(new Position(0, 0), new Position(0, 0), new Position(0, 0),
                new Position(0, 0), new Position(0, 0), new Position(0, 0), new Position(0, 0),
                new Position(0, 0), new Position(0, 0), new Position(0, 0));
        visitedByTPositions.add(new Position(0, 0));
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
            var head = knots.get(0);
            knots.set(0, new Position(head.x() + x, head.y() + y));
            var prevPos = knots.get(0);

            for (int knotIndex = 1; knotIndex < knots.size(); knotIndex++) {
                var next = knots.get(knotIndex);
                if (isInPrevRange(prevPos, next)) {
                    prevPos = next;
                    continue;
                }
                if (prevPos.y() == next.y() || prevPos.x() == next.x()) {
                    var moveX = (prevPos.x() - next.x()) / 2;
                    var moveY = (prevPos.y() - next.y()) / 2;
                    next = new Position(next.x() + moveX, next.y() + moveY);
                } else {
                    next = findTPosition(prevPos, next);
                }

                knots.set(knotIndex, new Position(next.x(), next.y()));
                prevPos = knots.get(knotIndex);
                if (knotIndex == (knots.size() - 1)) {
                    visitedByTPositions.add(prevPos);
                }
            }
        }
    }

    private boolean isInPrevRange(Position prev, Position next) {
        return Math.abs(prev.x() - next.x()) <= 1 && Math.abs(prev.y() - next.y()) <= 1;
    }

    private Position findTPosition(Position prev, Position next) {
        var possiblePositions = Stream.of(new Position(prev.x() + 1, prev.y()), new Position(prev.x() - 1, prev.y()),
                        new Position(prev.x(), prev.y() + 1), new Position(prev.x(), prev.y() - 1))
                .filter(pos -> pos.x() != next.x() && pos.y() != next.y())
                .filter(pos -> calculateDistanceFromPrev(pos, next) <= 2)
                .toList();

        if (possiblePositions.isEmpty()) {
            possiblePositions = List.of(new Position(prev.x() + 1, prev.y() + 1), new Position(prev.x() - 1, prev.y() - 1),
                    new Position(prev.x() - 1, prev.y() + 1), new Position(prev.x() + 1, prev.y() - 1));
        }

        return possiblePositions.stream()
                .min(Comparator.comparingInt(pos -> calculateDistanceFromPrev(pos, next)))
                .orElseThrow(IllegalAccessError::new);
    }

    private int calculateDistanceFromPrev(Position pos, Position next) {
        return Math.abs(pos.x() - next.x()) + Math.abs(pos.y() - next.y());
    }

    public int getNumberOfTVisitedPositions() {
        return visitedByTPositions.size();
    }
}
