package challenges.day11;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
                    item = new BigDecimal(monkey.applyOperation(item)).divide(DIVIDER, RoundingMode.DOWN).intValue();
                    var toIndex = monkey.throwTo(item);
                    monkeys.get(toIndex).addItem(item);
                } catch (NoSuchElementException e) {
                    hasItems = false;
                }
            }
        }
    }
}
