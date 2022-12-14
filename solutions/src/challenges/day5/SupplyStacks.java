package challenges.day5;

import challenges.Solver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SupplyStacks implements Solver<String> {
    private static List<String> loadLines() {
        var f = new File("src/challenges/day5/input.txt");
        var sb = new StringBuilder();
        try (var fr = new FileReader(f);
             var br = new BufferedReader(fr)) {
            int c;
            while ((c = br.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return Arrays.stream(sb.toString().split("\\r\\n"))
                .toList();
    }

    @Override
    public String solve() {
        List<String> lines = loadLines();
        var stacksLoaded = false;
        var supply = new Supply();
        for (var line : lines) {
            if (line.equals("")) {
                supply.revert();
                stacksLoaded = true;
                continue;
            }

            if (stacksLoaded) {
                supply.apply(new Move(line));
            } else {
                supply.parseLine(line);
            }
        }

        return supply.getResult();
    }
}
