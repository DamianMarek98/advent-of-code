package challenges.day6;

public class DataStreamProcessor {
    private final String stream;

    public DataStreamProcessor(String stream) {
        this.stream = stream;
    }

    public int findFirstStartOfPacketMarkerPosition() {
        return findFirstDistinctPacketWithLength(4);
    }

    private int findFirstDistinctPacketWithLength(int length) {
        for (int i = length - 1; i < stream.length(); i++) {
            boolean repetition = false;
            String test = String.valueOf(stream.charAt(i));
            for (int j = i - length + 1; j < i; j++) {
                final String character = String.valueOf(stream.charAt(j));
                if (test.contains(character)) {
                    repetition = true;
                    break;
                }
                test += character;
            }

            if (!repetition) {
                return i + 1;
            }
        }

        return 0;
    }

    public int findFirstStartOfMessageMarkerPosition() {
        return findFirstDistinctPacketWithLength(14);
    }
}
