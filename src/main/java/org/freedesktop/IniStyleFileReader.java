package org.freedesktop;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Allows reading Desktop Entry files.
 */
public class IniStyleFileReader {
	/* TODO: preserve comments */

    private BufferedReader reader;

    public IniStyleFileReader(BufferedReader reader) {
        this.reader = reader;
    }

    public IniStyleFile read() throws IOException {

        IniStyleFile iniStyleFile = new IniStyleFile();
        String line = null;
        String groupName = null;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("[") && line.endsWith("]")) {
                groupName = line.substring(1, line.length() - 1);
                iniStyleFile.addGroup(groupName);
            } else if (groupName != null) {
                String[] parts = line.split("=");
                String key = parts[0].trim();
                String value = "";
                if (parts.length > 1) {
                    value = parts[1].trim();
                }
                iniStyleFile.add(groupName, key, value);
            }
        }

        return iniStyleFile;
    }
}
