package challenges.day11;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.function.Function;

public class Monkey {
    private final Function<Integer, Integer> operation;
    private final String operator;
    private final boolean multiply;
    private final int divisibleBy;
    private final int ifTrueThrowToIndex;
    private final int ifFalseThrowToIndex;
    private ArrayDeque<Integer> items = new ArrayDeque<>();
    private int inspectionCounter = 0;

    public Monkey(String startingItems, String operation, String test, String ifTrue, String ifFalse) {
        Arrays.stream(startingItems.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .forEach(val -> items.addLast(val));
        multiply = operation.contains("*");
        operator = operation.replace("old", "").replace("*", "").replace("+", "").trim();
        this.operation = val -> {
            var secondVal = operator.isEmpty() ? val : Integer.parseInt(operator);
            return multiply ? val * secondVal : val + secondVal;
        };
        divisibleBy = Integer.parseInt(test.trim());
        ifTrueThrowToIndex = Integer.parseInt(ifTrue.trim());
        ifFalseThrowToIndex = Integer.parseInt(ifFalse.trim());
    }

    public Integer applyOperation(Integer item) {
        return operation.apply(item);
    }

    public int throwTo(Integer item) {
        return item % divisibleBy == 0 ? ifTrueThrowToIndex : ifFalseThrowToIndex;
    }

    public void addItem(Integer item) {
        items.addLast(item);
    }

    public Integer inspectItem() {
        if (!items.isEmpty()) {
            inspectionCounter++;
        }
        return items.pop();
    }

    public int getInspectionCounter() {
        return inspectionCounter;
    }
}
