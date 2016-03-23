package org.freedesktop;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class IniStyleFileWriter {
    private final Writer writer;

    /**
     * @param writer the writer must be using a utf-8 encoding
     */
    public IniStyleFileWriter(Writer writer) {
        this.writer = writer;
    }

    public void write(IniStyleFile file) throws IOException {
        writeGroup(file, DesktopEntry.GROUP_DEFAULT);
        for (String name : file.data.keySet()) {
            if (!name.equals(DesktopEntry.GROUP_DEFAULT)) {
                writeGroup(file, name);
            }
        }
    }

    private void writeGroup(IniStyleFile file, String groupName) throws IOException {
        if (!file.data.containsKey(groupName)) {
            return;
        }

        writer.write("[" + groupName + "]\n");
        Map<String, String> group = file.data.get(groupName);
        for (String key : group.keySet()) {
            writer.write(key + "=" + group.get(key) + "\n");
        }
    }
}
