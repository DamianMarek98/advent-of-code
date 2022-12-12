package challenges.day3;

import challenges.Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RucksackReorganization implements Solver<Integer> {

    @Override
    public Integer solve() {
        var prioritySum = 0;
        try (Scanner scanner = new Scanner(new File("src/challenges/day3/input.txt"))) {
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                //prioritySum += new Rucksack(line).getDuplicatePriority();
                prioritySum += new Group(line, scanner.nextLine(), scanner.nextLine()).getDuplicatePriority();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return prioritySum;
    }
}
