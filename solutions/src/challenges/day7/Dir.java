package challenges.day7;

import java.util.ArrayList;
import java.util.List;

public class Dir {
    private final Dir parent;
    private final List<Dir> subDirs = new ArrayList<>();
    private final List<File> files = new ArrayList<>();

    public Dir(Dir parent) {
        this.parent = parent;
    }

    public Dir getParent() {
        return parent;
    }

    public void addSubDir(Dir dir) {
        subDirs.add(dir);
    }

    public void addFile(File file) {
        files.add(file);
    }

    public int calculateSize() {
        return files.stream().mapToInt(File::getSize).sum() + subDirs.stream().mapToInt(Dir::calculateSize).sum();
    }
}
