package challenges.day5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Supply {
    private final List<Deque<Character>> stacks = new ArrayList<>();

    public void parseLine(String line) {
        if (line.charAt(1) == '1') {
            return;
        }

        var dequeIndex = 0;
        for (int i = 1; i < line.length(); i += 4) {
            var letter = line.charAt(i);

            if (stacks.size() == dequeIndex) {
                stacks.add(new ArrayDeque<>());
            }

            var deque = stacks.get(dequeIndex);
            if (letter != ' ') {
                deque.push(letter);
            }
            dequeIndex++;
        }
    }

    public void revert() {
        for (int i = 0; i < stacks.size(); i++) {
            var deque = stacks.get(i);
            var dequeReverted = new ArrayDeque<Character>();
            for (var character : deque) {
                dequeReverted.push(character);
            }

            stacks.set(i, dequeReverted);
        }
    }

    public void apply(Move move) {
        var from = stacks.get(move.getFormIndex());
        var to = stacks.get(move.getToIndex());
        if (!move.isMultipleCratesAtOne()) {
            for (int i = 0; i < move.getHowMany(); i++) {
                to.push(from.pop());
            }
            return;
        }

        var crates = new ArrayList<Character>();
        for (int i = 0; i < move.getHowMany(); i++) {
            crates.add(from.pop());
        }
        for (int i = crates.size() - 1; i >= 0; i--) {
            to.push(crates.get(i));
        }
    }

    public String getResult() {
        return stacks.stream()
                .map(Deque::pop)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
