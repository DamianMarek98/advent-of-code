package challenges.day8;

import challenges.FileLineIterator;
import challenges.Solver;

import java.util.ArrayList;

public class TreetopTreeHouse implements Solver<Integer> {
    @Override
    public Integer solve() {
        var lines = new ArrayList<String>();
        for (var line : new FileLineIterator(8, "input")) {
            lines.add(line);
        }
        var treeGrid = new TreeGrid(lines);
        return treeGrid.countVisibleTrees();
    }
}
