package org.freedesktop;

import java.io.BufferedReader;
import java.io.IOException;

public class DesktopEntryReader {

    private IniStyleFileReader delegate;

    public DesktopEntryReader(BufferedReader reader) {
        delegate = new IniStyleFileReader(reader);
    }

    public DesktopEntry read() throws IOException {
        return new DesktopEntry(delegate.read());
    }

}
