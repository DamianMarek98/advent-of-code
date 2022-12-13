package challenges.day3;

import challenges.FileLineIterator;
import challenges.Solver;

public class RucksackReorganization implements Solver<Integer> {

    @Override
    public Integer solve() {
        var prioritySum = 0;
        var linesIterator = new FileLineIterator(3, "input").iterator();
        while (linesIterator.hasNext()) {
            //prioritySum += new Rucksack(linesIterator.next()).getDuplicatePriority();
            prioritySum += new Group(linesIterator.next(), linesIterator.next(), linesIterator.next()).getDuplicatePriority();
        }


        return prioritySum;
    }
}
