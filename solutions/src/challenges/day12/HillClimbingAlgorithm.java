package challenges.day12;

import challenges.Solver;
import challenges.iteration.FileIteratorDirector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HillClimbingAlgorithm implements Solver<Integer> {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    @Override
    public Integer solve() {
        var lines = new ArrayList<String>();
        var width = 0;
        var height = 0;
        for (var line : new FileIteratorDirector().createFileLineIterator(12, true)) {
            if (width == 0) {
                width = line.length();
            }
            lines.add(line);
            height++;
        }
        var grid = new Grid(lines, width, height);

        var stepsPerA = new ArrayList<List<Coordinate>>();
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                grid = new Grid(lines, width, height);
                List<Coordinate> points;
                LinkedList<Coordinate> nextToVisit = new LinkedList<>();
                if (grid.grid[i][j] == 97) {
                    grid.setStart(new Coordinate(i, j, null));
                    Coordinate start = grid.getEntry();
                    nextToVisit.add(start);

                    while (!nextToVisit.isEmpty()) {
                        Coordinate cur = nextToVisit.remove();
                        if (!grid.isValidLocation(cur.row(), cur.col()) || grid.isExplored(cur.row(), cur.col()) || grid.cantMove(cur)) {
                            continue;
                        }

                        if (grid.isExit(cur.row(), cur.col())) {
                            points = backtrackPath(cur);
                            stepsPerA.add(points);
                            nextToVisit.clear();
                        } else {
                            for (int[] direction : DIRECTIONS) {
                                Coordinate coordinate = new Coordinate(cur.row() + direction[0], cur.col() + direction[1], cur);
                                nextToVisit.add(coordinate);
                                grid.setVisited(cur.row(), cur.col(), true);
                            }
                        }
                    }

                }
            }
        }


        return stepsPerA.stream()
                .map(points -> points.size() - 1)
                .mapToInt(Integer::intValue)
                .min()
                .orElse(0);
    }

    private List<Coordinate> backtrackPath(
            Coordinate cur) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = iter.parent();
        }

        return path;
    }
}
