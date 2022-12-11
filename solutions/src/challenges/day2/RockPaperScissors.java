package challenges.day2;

import challenges.Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RockPaperScissors implements Solver<Integer> {

    @Override
    public Integer solve() {
        var totalPoints = 0;
        try (Scanner scanner = new Scanner(new File("src/challenges/day2/input.txt"))) {
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                totalPoints += new Game(line).calculatePoints();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return totalPoints;
    }
}
