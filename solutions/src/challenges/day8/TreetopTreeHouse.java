package challenges.day8;

import challenges.Solver;
import challenges.iteration.FileIteratorDirector;

import java.util.ArrayList;

public class TreetopTreeHouse implements Solver<Integer> {
    @Override
    public Integer solve() {
        var lines = new ArrayList<String>();
        for (var line : new FileIteratorDirector().createFileLineIterator(8, true)) {
            lines.add(line);
        }
        var treeGrid = new TreeGrid(lines);
        return treeGrid.calculateHighestScenicScore();
    }
}
