package challenges.day3;

public class Group implements DuplicatePriorityResolver {
    private final String set1;
    private final String set2;
    private final String set3;

    public Group(String set1, String set2, String set3) {
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
    }

    @Override
    public int getDuplicatePriority() {
        var item = getDuplicateItem();
        return PriorityCalculator.calculate(item);
    }

    private char getDuplicateItem() {
        for (int i = 0; i < set1.length(); i++) {
            final char item = set1.charAt(i);
            if (set2.indexOf(item) == -1 || set3.indexOf(item) == -1) {
                continue;
            }

            return item;
        }

        throw new IllegalStateException();
    }
}
