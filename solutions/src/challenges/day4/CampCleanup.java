package challenges.day4;

import challenges.FileLineIterator;
import challenges.Solver;

import java.util.ArrayList;

public class CampCleanup implements Solver<Integer> {
    @Override
    public Integer solve() {
        var elvesPairs = new ArrayList<ElvesPair>();
        for (var line : new FileLineIterator(4, "input")) {
            elvesPairs.add(new ElvesPair(line));
        }

        return (int) elvesPairs.stream()
                .filter(ElvesPair::isAssigmentOverlapping)
                .count();
    }
}
