package challenges.day7;

import challenges.FileLineIterator;
import challenges.Solver;

import java.util.HashSet;

public class NoSpaceLeftOnDevice implements Solver<Integer> {

    private static final String ONLY_DIGITS_REGEX = "\\d+";
    private Dir currentDir;

    @Override
    public Integer solve() {
        String prev = "";
        var dirs = new HashSet<Dir>();
        for (var element : new FileLineIterator(7, "example")) {
            if (element.equals("$") || element.equals("cd") || element.equals("ls") || element.equals("dir")
                    || element.matches(ONLY_DIGITS_REGEX) || prev.equals("dir")) {
                prev = element;
                continue;
            }

            if (prev.equals("cd") && element.equals("..")) {
                currentDir = currentDir.getParent();
            } else if (prev.equals("cd")) {
                var dir = new Dir(currentDir);
                dirs.add(dir);
                if (this.currentDir != null) {
                    currentDir.addSubDir(dir);
                }
                currentDir = dir;
            } else if (prev.matches(ONLY_DIGITS_REGEX)) {
                var file = new File(Integer.parseInt(prev));
                currentDir.addFile(file);
            }

            prev = element;
        }

        var result = 0;
        for (var dir : dirs) {
            final int size = dir.calculateSize();
            if (size < 100000) {
                result += size;
            }
        }

        return result;
    }
}
