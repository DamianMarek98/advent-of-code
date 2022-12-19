package challenges.day7;

import challenges.Solver;
import challenges.iteration.FileIteratorDirector;

import java.util.HashSet;

public class NoSpaceLeftOnDevice implements Solver<Integer> {

    private static final String ONLY_DIGITS_REGEX = "\\d+";
    private Dir currentDir;
    private static final int TOTAL_DISC_SPACE = 70000000;
    private static final int FREE_SPACE_NEEDED = 30000000;

    private static boolean isElementToSkip(String prev, String element) {
        return element.equals("$") || element.equals("cd") || element.equals("ls") || element.equals("dir")
                || element.matches(ONLY_DIGITS_REGEX) || prev.equals("dir");
    }

    private static boolean isComebackToParent(String prev, String element) {
        return prev.equals("cd") && element.equals("..");
    }

    private static int calculateResult(HashSet<Dir> dirs, Dir root) {
        var result = TOTAL_DISC_SPACE;
        if (root == null) {
            throw new IllegalStateException();
        }
        var usedSpace = root.calculateSize();
        for (var dir : dirs) {
            final int size = dir.calculateSize();
            if (FREE_SPACE_NEEDED <= TOTAL_DISC_SPACE - usedSpace + size && size < result) {
                result = size;
            }
        }

        return result;
    }

    @Override
    public Integer solve() {
        var dirs = new HashSet<Dir>();
        var root = createFileStructure(dirs);
        return calculateResult(dirs, root);
    }

    private Dir createFileStructure(HashSet<Dir> dirs) {
        String prev = "";
        Dir root = null;
        for (var element : new FileIteratorDirector().createFileElementIterator(7, true)) {
            if (isElementToSkip(prev, element)) {
                prev = element;
                continue;
            }

            if (isComebackToParent(prev, element)) {
                currentDir = currentDir.getParent();
            } else if (prev.equals("cd")) {
                var dir = new Dir(currentDir);
                dirs.add(dir);
                if (root == null) {
                    root = dir;
                }
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
        return root;
    }
}
