package challenges.day3;

import challenges.Solver;
import challenges.iteration.FileIteratorDirector;

public class RucksackReorganization implements Solver<Integer> {

    @Override
    public Integer solve() {
        var prioritySum = 0;
        var linesIterator = new FileIteratorDirector().createFileLineIterator(3, true).iterator();
        while (linesIterator.hasNext()) {
            //prioritySum += new Rucksack(linesIterator.next()).getDuplicatePriority();
            prioritySum += new Group(linesIterator.next(), linesIterator.next(), linesIterator.next()).getDuplicatePriority();
        }


        return prioritySum;
    }
}
