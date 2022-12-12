package challenges.day3;

public class PriorityCalculator {
    private PriorityCalculator() {
    }

    static int calculate(int item) {
        if (item > 96) {
            return item - 96;
        }

        return item - 38;
    }
}
