package challenges.day1;

import challenges.Solver;
import challenges.iteration.FileIteratorDirector;

public class CalorieCounting implements Solver<Integer> {
    @Override
    public Integer solve() {
        var topCalories = new TopCalories();
        var max = 0;
        var currentCalories = 0;
        for (var line : new FileIteratorDirector().createFileLineIterator(1, true)) {
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
