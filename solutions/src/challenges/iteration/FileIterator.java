package challenges.iteration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class FileIterator implements Iterable<String>, FileIteratorBuilder {

    private Scanner scanner;
    private boolean hasNext = true;
    private int day;
    private boolean readLines;
    private String filename;

    FileIterator() {
    }

    @Override
    public FileIteratorBuilder readExample(boolean readExample) {
        filename = readExample ? "example" : "input";
        return this;
    }

    @Override
    public FileIteratorBuilder readLine(boolean readLine) {
        readLines = readLine;
        return this;
    }

    @Override
    public FileIteratorBuilder setDay(int day) {
        this.day = day;
        return this;

    }

    @Override
    public FileIterator build() {
        try {
            scanner = new Scanner(new File("src/challenges/day" + day + "/" + filename + ".txt"));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException();
        }
        return this;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                if (hasNext) {
                    hasNext = readLines ? scanner.hasNextLine() : scanner.hasNext();
                    if (!hasNext) {
                        scanner.close();
                    }
                }
                return hasNext;
            }

            @Override
            public String next() {
                return readLines ? scanner.nextLine() : scanner.next();
            }
        };
    }

}
