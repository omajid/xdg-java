package org.freedesktop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TrashCan {

    private File home;

    public TrashCan() {
        this(new File(BaseDirectory.get(BaseDirectory.XDG_DATA_HOME) + "/" + "Trash"));
    }

    TrashCan(File homeTrash) {
        this.home = homeTrash;
    }

    /** The directory that serves as the main directory containing trash files */
    public File getHomeTrash() {
        return home;
    }

    /** Move a single file into the trash can */
    public void trash(File file) {
        // create info file
        // move file
    }

    public List<TrashFile> getTrashedFiles() {
        return new ArrayList<>();
    }

    public void undelete(TrashFile file) {

    }

    public void erase(TrashFile file) {

    }


}
