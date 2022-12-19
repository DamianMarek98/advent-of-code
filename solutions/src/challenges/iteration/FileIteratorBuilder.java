package challenges.iteration;

public interface FileIteratorBuilder {
    FileIteratorBuilder readExample(boolean readExample);

    FileIteratorBuilder readLine(boolean readLine);

    FileIteratorBuilder setDay(int day);

    FileIterator build();
}
