package challenges.day3;

public class Rucksack implements DuplicatePriorityResolver {
    private final String firstCompartment;
    private final String secondCompartment;

    public Rucksack(String compartment) {
        final int middleIndex = compartment.length() / 2;
        this.firstCompartment = compartment.substring(0, middleIndex);
        this.secondCompartment = compartment.substring(middleIndex);
    }

    @Override
    public int getDuplicatePriority() {
        var item = getDuplicateItem();
        return PriorityCalculator.calculate(item);
    }

    private char getDuplicateItem() {
        for (int i = 0; i < firstCompartment.length(); i++) {
            if (secondCompartment.indexOf(firstCompartment.charAt(i)) == -1) {
                continue;
            }

            return firstCompartment.charAt(i);
        }

        throw new IllegalStateException();
    }
}
