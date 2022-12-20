package challenges.day11;

import challenges.Solver;
import challenges.iteration.FileIteratorDirector;

import java.util.ArrayList;
import java.util.Comparator;

public class MonkeyInTheMiddle implements Solver<Integer> {

    private static final String STARTING_ITEMS = "Starting items: ";
    private static final String OPERATION = "Operation: new = ";
    private static final String TEST = "Test: divisible by ";
    private static final String IF_TRUE = "If true: throw to monkey ";
    private static final String IF_FALSE = "If false: throw to monkey ";


    @Override
    public Integer solve() {
        String startingItems = "";
        String operation = "";
        String test = "";
        String ifTrue = "";
        String ifFalse = "";
        var monkeys = new ArrayList<Monkey>();
        for (var line : new FileIteratorDirector().createFileLineIterator(11, false)) {
            if (line.contains("Monkey") || line.equals("")) {
                continue;
            }

            if (line.contains(STARTING_ITEMS)) {
                startingItems = line.replace(STARTING_ITEMS, "");
            } else if (line.contains(OPERATION)) {
                operation = line.replace(OPERATION, "");
            } else if (line.contains(TEST)) {
                test = line.replace(TEST, "");
            } else if (line.contains(IF_TRUE)) {
                ifTrue = line.replace(IF_TRUE, "");
            } else if (line.contains(IF_FALSE)) {
                ifFalse = line.replace(IF_FALSE, "");

                monkeys.add(new Monkey(startingItems, operation, test, ifTrue, ifFalse));
            }
        }

        var round = new Round();
        for (int i = 0; i < 20; i++) {
            round.process(monkeys);
        }

        return monkeys.stream()
                .map(Monkey::getInspectionCounter)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce(1, (a, b) -> a * b);
    }
}
