package challenges.day13;

import java.util.ArrayList;
import java.util.List;

public class Packet {
    private final List<Element> elements = new ArrayList<>();

    public Packet(String line) {
        line = line.substring(1, line.length() - 1).replace("10", "x");
        Element currentParent = null;
        for (var elem : line.toCharArray()) {
            if (elem == ',') {
                continue;
            }

            if (elem == '[') {
                currentParent = new Element(currentParent, null);
                elements.add(currentParent);
            } else if (elem == ']') {
                if (currentParent != null) {
                    currentParent = currentParent.parent();
                } else {
                    throw new IllegalStateException();
                }
            } else if (elem == 'x') {
                elements.add(new Element(currentParent, 10));
            } else {
                elements.add(new Element(currentParent, Character.getNumericValue((elem))));
            }
        }
    }

    public boolean compare(Packet rightPacket) {
        var rightElementsSize = rightPacket.elements.size();
        var rightIndex = 0;
        for (int i = 0; i < elements.size(); i++) {
            if (rightIndex == rightElementsSize) {
                return false;
            }
            var element = elements.get(i);
            var rightElement = rightPacket.elements.get(rightIndex);
            var comparisonDone = false;
            while (!comparisonDone) {
                if (rightIndex == rightElementsSize) {
                    return false;
                }

                if (element.isInteger() && rightElement.isInteger()) {
                    if (element.integer() < rightElement.integer()) {
                        return true;
                    } else if (element.integer().equals(rightElement.integer())) {
                        comparisonDone = true;
                        rightIndex++;
                    } else {
                        return false;
                    }
                } else if (element.isInteger()) {
                    rightIndex++;
                    rightElement = rightPacket.elements.get(rightIndex);
                } else if (!rightElement.isInteger()) {
                    rightIndex++;
                    comparisonDone = true;
                } else {
                    comparisonDone = true;
                }
            }
        }

        return true;
    }
}
