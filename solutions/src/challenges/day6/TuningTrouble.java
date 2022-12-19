package challenges.day6;

import challenges.Solver;
import challenges.iteration.FileIteratorDirector;

public class TuningTrouble implements Solver<Integer> {
    @Override
    public Integer solve() {
        for (var line : new FileIteratorDirector().createFileLineIterator(6, true)) {
            return new DataStreamProcessor(line).findFirstStartOfMessageMarkerPosition();
        }
        return null;
    }
}
