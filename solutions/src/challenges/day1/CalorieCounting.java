package challenges.day1;

import challenges.Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CalorieCounting implements Solver<Integer> {
    @Override
    public Integer solve() {
        var topCalories = new TopCalories();
        var max = 0;
        var currentCalories = 0;
        try (Scanner scanner = new Scanner(new File("src/challenges/day1/input.txt"))) {
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                if (line.equals("")) {
                    if (max < currentCalories) {
                        max = currentCalories;
                    }
                    topCalories.handleNewValue(currentCalories);
                    currentCalories = 0;
                } else {
                    currentCalories += Integer.parseInt(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (max < currentCalories) {
            max = currentCalories;
        }

        if (currentCalories != 0) {
            topCalories.handleNewValue(currentCalories);
        }

        return topCalories.sum();
    }

    private static class TopCalories {
        private int min = 0;
        private int mid = 0;
        private int max = 0;

        public void handleNewValue(int value) {
            if (value >= max) {
                min = mid;
                mid = max;
                max = value;
            } else if (value >= mid) {
                min = mid;
                mid = value;
            } else if (value > min) {
                min = value;
            }
        }

        public int sum() {
            return min + mid + max;
        }
    }
}
