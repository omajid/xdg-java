package org.freedesktop;

import java.text.DateFormat;
import java.util.Date;

public class TrashInfo extends IniStyleFile {

    public static final String KEY_PATH = "Path";
    public static final String KEY_DELETION_DATE = "DeletionDate";

    public TrashInfo() {
        super("Trash Info");
    }

    public String getPath() {
        return get(KEY_PATH);
    }

    public void setPath(String path) {
        add(KEY_PATH, path);
    }

    public Date getDeletionDate() {
        return null;
    }

    public void setDeletionDate(Date date) {
        add(KEY_DELETION_DATE, null);
    }
}
