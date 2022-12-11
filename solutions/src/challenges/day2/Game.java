package challenges.day2;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.function.Function;

public class Game {
    private final Figure playerFigure;
    private final Figure opponentFigure;
    private final List<Figure> figures = List.of(Figure.values());

    public Game(String inputLine) {
        playerFigure = extractFigure(inputLine, Figure::getPlayerSign);
        opponentFigure = extractFigure(inputLine, Figure::getOpponentSign);
    }

    private Figure extractFigure(String inputLine, Function<Figure, String> getSign) {
        return figures.stream()
                .filter(figure -> inputLine.contains(getSign.apply(figure)))
                .findFirst()
                .orElseThrow(InvalidParameterException::new);
    }

    public int calculatePoints() {
        var points = playerFigure.getPoints();

        if (playerFigure.equals(opponentFigure)) {
            return points + 3;
        } else if (playerWon()) {
            return points + 6;
        }

        return points;
    }

    private boolean playerWon() {
        return (playerFigure.equals(Figure.SCISSORS) && opponentFigure.equals(Figure.PAPER)) ||
                (playerFigure.equals(Figure.PAPER) && opponentFigure.equals(Figure.ROCK)) ||
                (playerFigure.equals(Figure.ROCK) && opponentFigure.equals(Figure.SCISSORS));
    }
}
