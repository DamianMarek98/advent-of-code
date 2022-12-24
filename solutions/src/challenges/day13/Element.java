package challenges.day13;

public record Element(challenges.day13.Element parent, Integer integer) {

    public boolean isInteger() {
        return integer != null;
    }
}
