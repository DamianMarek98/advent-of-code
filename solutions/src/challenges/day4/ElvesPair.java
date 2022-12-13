package challenges.day4;

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
}
