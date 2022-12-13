package challenges.day4;

import java.util.HashSet;
import java.util.Set;

public class ElvesPair {
    private static final char SEPARATOR = ';';
    private final String assignmentA;
    private final String assignmentB;

    public ElvesPair(String line) {
        var assigment = line.split(",", 2);
        assignmentA = buildAssigmentFromInput(assigment[0]);
        assignmentB = buildAssigmentFromInput(assigment[1]);
    }

    private String buildAssigmentFromInput(String inputAssigment) {
        final int rangeSeparatorIndex = inputAssigment.indexOf("-");
        var from = Integer.parseInt(inputAssigment.substring(0, rangeSeparatorIndex));
        var to = Integer.parseInt(inputAssigment.substring(rangeSeparatorIndex + 1));
        var sb = new StringBuilder();
        for (int i = from; i <= to; i++) {
            sb.append(SEPARATOR).append(i).append(SEPARATOR);
        }

        return sb.toString();
    }

    public boolean isAssigmentContained() {
        return assignmentA.contains(assignmentB) || assignmentB.contains(assignmentA);
    }

    public boolean isAssigmentOverlapping() {
        var setA = Set.of(assignmentA.substring(1, assignmentA.length() - 1).split(";;"));
        var setB = Set.of(assignmentB.substring(1, assignmentB.length() - 1).split(";;"));
        int sizeSum = setA.size() + setB.size();
        var setMerge = new HashSet<String>();
        setMerge.addAll(setA);
        setMerge.addAll(setB);
        return sizeSum != setMerge.size();
    }
}
