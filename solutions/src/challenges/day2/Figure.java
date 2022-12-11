package challenges.day2;

enum Figure {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3);

    private final String opponentSign;
    private final String playerSign;
    private final int points;


    Figure(String opponentSign, String playerSign, int points) {
        this.opponentSign = opponentSign;
        this.playerSign = playerSign;
        this.points = points;
    }

    public String getOpponentSign() {
        return opponentSign;
    }

    public String getPlayerSign() {
        return playerSign;
    }

    public int getPoints() {
        return points;
    }
}
