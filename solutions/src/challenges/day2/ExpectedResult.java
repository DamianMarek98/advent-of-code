package challenges.day2;

public enum ExpectedResult {
    LOSE("X", 0),
    DRAW("Y", 3),
    WIN("Z", 6);

    private final String sign;
    private final int points;

    ExpectedResult(String sign, int points) {
        this.sign = sign;
        this.points = points;
    }

    public String getSign() {
        return sign;
    }

    public int getPoints() {
        return points;
    }
}
