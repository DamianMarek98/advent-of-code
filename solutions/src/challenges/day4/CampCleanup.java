package challenges.day4;

import challenges.Solver;
import challenges.iteration.FileIteratorDirector;

import java.util.ArrayList;

public class CampCleanup implements Solver<Integer> {
    @Override
    public Integer solve() {
        var elvesPairs = new ArrayList<ElvesPair>();
        for (var line : new FileIteratorDirector().createFileLineIterator(4, true)) {
            elvesPairs.add(new ElvesPair(line));
        }

        return (int) elvesPairs.stream()
                .filter(ElvesPair::isAssigmentOverlapping)
                .count();
    }
}
