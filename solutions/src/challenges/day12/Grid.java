package challenges.day12;

import java.util.List;

public class Grid {
    public static final int Z = 122;
    private static final int S = 83;
    private static final int E = 69;
    public boolean[][] visited;
    int width = 0;
    int height = 0;
    int[][] grid;
    private Coordinate start;
    private Coordinate end;

    public Grid(List<String> lines, int width, int height) {
        this.width = width;
        this.height = height;
        grid = new int[height][width];
        visited = new boolean[height][width];
        var curRow = 0;
        for (var line : lines) {
            var curCol = 0;
            for (var elem : line.chars().boxed().toList()) {
                if (elem.equals(S)) {
                    start = new Coordinate(curRow, curCol, null);
                    elem = 97;
                } else if (elem.equals(E)) {
                    end = new Coordinate(curRow, curCol, null);
                }
                grid[curRow][curCol] = elem;
                visited[curRow][curCol] = false;
                curCol++;
            }
            curRow++;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public Coordinate getEntry() {
        return start;
    }

    public void setStart(Coordinate start) {
        this.start = start;
    }

    public Coordinate getExit() {
        return end;
    }

    public boolean isExit(int x, int y) {
        return x == end.row() && y == end.col();
    }

    public boolean isStart(int x, int y) {
        return x == start.row() && y == start.col();
    }

    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    public boolean isValidLocation(int row, int col) {
        return row >= 0 && row < getHeight() && col >= 0 && col < getWidth();
    }

    public boolean cantMove(Coordinate cur) {
        var parent = cur.parent();
        if (parent == null) {
            return false;
        }
        final int valNew = grid[cur.row()][cur.col()];
        final int curVal = grid[parent.row()][parent.col()];
        final int difference = valNew - curVal;
        if (curVal == Z && valNew == E) {
            return false;
        }
//        if (valNew == E) {
//            return difference != 1;
//        }
        return difference > 1;
    }
}
