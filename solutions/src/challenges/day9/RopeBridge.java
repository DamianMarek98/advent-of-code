package challenges.day9;

import challenges.FileLineIterator;
import challenges.Solver;

public class RopeBridge implements Solver<Integer> {
    private static final String ONLY_DIGITS_REGEX = "\\d+";
    @Override
    public Integer solve() {
        var grid = new Grid();
        var previousElem = "";
        for (var elem : new FileLineIterator(9, false)) {
            if (elem.matches(ONLY_DIGITS_REGEX)) {
                grid.move(Integer.parseInt(elem), previousElem);
            }
            previousElem = elem;
        }

        return grid.getNumberOfTVisitedPositions();
    }
}
