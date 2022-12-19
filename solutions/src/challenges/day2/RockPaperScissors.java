package challenges.day2;

import challenges.Solver;
import challenges.iteration.FileIteratorDirector;

public class RockPaperScissors implements Solver<Integer> {

    @Override
    public Integer solve() {
        var totalPoints = 0;
        for (var line : new FileIteratorDirector().createFileLineIterator(2, true)) {
            totalPoints += new Game(line).calculatePoints();
        }


        return totalPoints;
    }
}
