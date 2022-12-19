package challenges.day10;

import challenges.FileLineIterator;
import challenges.Solver;

public class CathodeRayTube implements Solver<Integer> {

    private int cycle = 0;
    private int registerXValue = 1;

    @Override
    public Integer solve() {
        var prevElement = "";
        var result = 0;
        var cycleToAdd = 20;

        var drawingIndex = 0;
        var sb = new StringBuilder();
        for (var element : new FileLineIterator(10, false)) {
            cycle++;
            drawingIndex++;
            if (drawingIndex >= registerXValue && drawingIndex < registerXValue + 3) {
                sb.append("#");
            } else {
                sb.append(".");
            }

            if (cycle % 40 == 0) {
                sb.append("\n");
                drawingIndex = 0;
            }

            if (cycle == cycleToAdd) {
                result += cycle * registerXValue;
                cycleToAdd += 40;
            }

            if (prevElement.equals("addx")) {
                registerXValue += Integer.parseInt(element);
            }

            prevElement = element;
        }

        System.out.println(sb);
        return result;
    }
}
