package challenges.day2;

import challenges.FileLineIterator;
import challenges.Solver;

public class RockPaperScissors implements Solver<Integer> {

    @Override
    public Integer solve() {
        var totalPoints = 0;
        for (var line : new FileLineIterator(2, "input")) {
            totalPoints += new Game(line).calculatePoints();
        }


        return totalPoints;
    }
}
