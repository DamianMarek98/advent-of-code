package challenges.iteration;

public class FileIteratorDirector {
    public FileIterator createFileLineIterator(int day, boolean example) {
        return new FileIterator()
                .readLine(true)
                .setDay(day)
                .readExample(example)
                .build();
    }

    public FileIterator createFileElementIterator(int day, boolean example) {
        return new FileIterator()
                .readLine(false)
                .setDay(day)
                .readExample(example)
                .build();
    }
}
