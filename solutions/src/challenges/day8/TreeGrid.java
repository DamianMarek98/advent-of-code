package challenges.day8;

import java.util.ArrayList;
import java.util.List;

public class TreeGrid {
    private final List<List<Integer>> treeSizes = new ArrayList<>();
    private final int x;
    private final int y;

    public TreeGrid(List<String> inputLines) {
        x = inputLines.size() - 1;
        y = inputLines.get(0).length() - 1;
        for (var line : inputLines) {
            if (treeSizes.isEmpty()) {
                for (int i = 0; i < line.length(); i++) {
                    treeSizes.add(new ArrayList<>());
                }
            }

            for (int i = 0; i < line.length(); i++) {
                treeSizes.get(i).add(Character.getNumericValue(line.charAt(i)));
            }
        }
    }

    public int countVisibleTrees() {
        int result = 2 * x + 2 * y;
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                final Integer height = treeSizes.get(i).get(j);
                if (isVisibleInColumn(i, j, height) || isVisibleInRow(j, i, height)) {
                    result++;
                }
            }
        }

        return result;
    }

    private boolean isVisibleInColumn(int col, int row, int height) {
        boolean isVisibleFromTop = true;
        for (int i = 0; i < row; i++) {
            if (treeSizes.get(col).get(i) >= height) {
                isVisibleFromTop = false;
            }
        }
        boolean isVisibleFromBottom = true;
        for (int i = row + 1; i <= y; i++) {
            if (treeSizes.get(col).get(i) >= height) {
                isVisibleFromBottom = false;
            }
        }

        return isVisibleFromTop || isVisibleFromBottom;
    }

    private boolean isVisibleInRow(int row, int col, int height) {
        boolean isVisibleFromLeft = true;
        for (int i = 0; i < col; i++) {
            if (treeSizes.get(i).get(row) >= height) {
                isVisibleFromLeft = false;
            }
        }
        boolean isVisibleFromRight = true;
        for (int i = col + 1; i <= x; i++) {
            if (treeSizes.get(i).get(row) >= height) {
                isVisibleFromRight = false;
            }
        }

        return isVisibleFromLeft || isVisibleFromRight;
    }
}
