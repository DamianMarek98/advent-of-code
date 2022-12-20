package challenges.day11;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

public class Round {
    private static final BigDecimal DIVIDER = BigDecimal.valueOf(3);

    public void process(List<Monkey> monkeys) {
        for (Monkey monkey : monkeys) {
            var hasItems = true;
            while (hasItems) {
                try {
                    var item = monkey.inspectItem();
                    //item = new BigDecimal(monkey.applyOperation(item)).divide(DIVIDER, RoundingMode.DOWN).longValue();
                    item = monkey.applyOperation(item);
                    item %= 9699690;
                    //item %= 96577;
                    var toIndex = monkey.throwTo(item);
                    monkeys.get(toIndex).addItem(item);
                } catch (NoSuchElementException e) {
                    hasItems = false;
                }
            }
        }
    }
}
