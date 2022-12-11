package challenges.day2;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Game {
    private final Figure playerFigure;
    private final Figure opponentFigure;
    private final ExpectedResult expectedResult;
    private final List<Figure> figures = List.of(Figure.values());
    private final List<ExpectedResult> results = List.of(ExpectedResult.values());
    private final Map<Figure, Figure> playerOpponentWin = Map.of(
            Figure.SCISSORS, Figure.PAPER,
            Figure.PAPER, Figure.ROCK,
            Figure.ROCK, Figure.SCISSORS);

    public Game(String inputLine) {
        playerFigure = extractFigure(inputLine, Figure::getPlayerSign);
        opponentFigure = extractFigure(inputLine, Figure::getOpponentSign);
        expectedResult = extractResult(inputLine);
    }

    private Figure extractFigure(String inputLine, Function<Figure, String> getSign) {
        return figures.stream()
                .filter(figure -> inputLine.contains(getSign.apply(figure)))
                .findFirst()
                .orElseThrow(InvalidParameterException::new);
    }

    private ExpectedResult extractResult(String inputLine) {
        return results.stream()
                .filter(er -> inputLine.contains(er.getSign()))
                .findFirst()
                .orElseThrow(InvalidParameterException::new);
    }

    public int calculatePoints() {
//        var points = playerFigure.getPoints();
//
//        if (playerFigure.equals(opponentFigure)) {
//            return points + 3;
//        } else if (playerWon()) {
//            return points + 6;
//        }
        var points = expectedResult.getPoints();
        points += figureOutPlayerFigure().getPoints();

        return points;
    }

    private boolean playerWon() {
        return playerOpponentWin.get(playerFigure).equals(opponentFigure);
    }

    public Figure figureOutPlayerFigure() {
        if (expectedResult.equals(ExpectedResult.DRAW)) {
            return opponentFigure;
        } else if (expectedResult.equals(ExpectedResult.LOSE)) {
            return playerOpponentWin.get(opponentFigure);
        } else {
            return playerOpponentWin.entrySet()
                    .stream().filter(entry -> entry.getValue()
                            .equals(opponentFigure))
                    .findFirst()
                    .orElseThrow(IllegalStateException::new).
                    getKey();
        }
    }
}
