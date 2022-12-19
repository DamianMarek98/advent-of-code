package challenges.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Monkey {
    private List<Integer> items = new ArrayList<>();
    private Function<Integer, Integer> operation;
    private int divisibleBy;
    private Monkey ifTrueThrowTo;
    private Monkey ifFalseThrowTo;
}
