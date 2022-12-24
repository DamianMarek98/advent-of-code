package challenges.day13;

import challenges.Solver;
import challenges.iteration.FileIteratorDirector;

import java.util.HashMap;

public class DistressSignal implements Solver<Integer> {

    @Override
    public Integer solve() {
        var packetsPairs = new HashMap<Integer, Pair>();

        Packet key = null;
        Packet val;
        var previousLine = "";
        var index = 1;
        for (var line : new FileIteratorDirector().createFileLineIterator(13, false)) {
            if (previousLine.equals("")) {
                key = new Packet(line);
            } else if (!line.equals("")) {
                val = new Packet(line);
                packetsPairs.put(index, new Pair(key, val));
                index++;
            }

            previousLine = line;
        }

        var result = 0;
        for (var entry : packetsPairs.entrySet()) {
            if (entry.getValue().left().compare(entry.getValue().right())) {
                result += entry.getKey();
            }
        }
        return result;
    }
}
