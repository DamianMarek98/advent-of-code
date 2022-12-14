package challenges.day5;

public class Move {
    private final int howMany;
    private final int formIndex;
    private final int toIndex;
    private final boolean multipleCratesAtOne;

    public Move(String line, boolean multipleCratesAtOne) {
        this.multipleCratesAtOne = multipleCratesAtOne;
        line = line.replace("move ", "").replace(" from ", ",").replace(" to ", ",");
        var values = line.split(",");
        howMany = Integer.parseInt(values[0]);
        formIndex = Integer.parseInt(values[1]) - 1;
        toIndex = Integer.parseInt(values[2]) - 1;
    }

    public int getHowMany() {
        return howMany;
    }

    public int getFormIndex() {
        return formIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public boolean isMultipleCratesAtOne() {
        return multipleCratesAtOne;
    }
}
