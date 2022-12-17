package challenges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class FileLineIterator implements Iterable<String> {

    private final Scanner scanner;
    private boolean hasNext = true;

    public FileLineIterator(int day, boolean example) {
        var fileName = example ? "example" : "input";
        try {
            scanner = new Scanner(new File("src/challenges/day" + day + "/" + fileName + ".txt"));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                if (hasNext) {
                    hasNext = scanner.hasNext();
                    if (!hasNext) {
                        scanner.close();
                    }
                }
                return hasNext;
            }

            @Override
            public String next() {
                return scanner.next();
            }
        };
    }

}
