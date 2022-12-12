package challenges.day3;

public class Rucksack {
    private final String firstCompartment;
    private final String secondCompartment;

    public Rucksack(String compartment) {
        final int middleIndex = compartment.length() / 2;
        this.firstCompartment = compartment.substring(0, middleIndex);
        this.secondCompartment = compartment.substring(middleIndex);
    }

    public int getDuplicatePriority() {
        var item = getDuplicateItem();
        var asciiValue = (int) item;
        if (asciiValue > 96) {
            return asciiValue - 96;
        }

        return asciiValue - 38;
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
