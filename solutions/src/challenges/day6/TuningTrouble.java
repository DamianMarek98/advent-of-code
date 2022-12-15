package challenges.day6;

import challenges.FileLineIterator;
import challenges.Solver;

public class TuningTrouble implements Solver<Integer> {
    @Override
    public Integer solve() {
        for (var line : new FileLineIterator(6, "input")) {
            return new DataStreamProcessor(line).findFirstStartOfMessageMarkerPosition();
        }
        return null;
    }
}
