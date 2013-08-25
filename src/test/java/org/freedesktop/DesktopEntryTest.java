package org.freedesktop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;

import org.junit.Test;

public class DesktopEntryTest {

    @Test
    public void testConstructor() {
        DesktopEntry entry = new DesktopEntry();
        assertNotNull(entry);
    }

    @Test
    public void testConstructor2() {
        IniStyleFile source = new IniStyleFile();
        assertNotNull(source);
        DesktopEntry entry = new DesktopEntry(source);
        assertNotNull(entry);
    }

    public DesktopEntry createBasicDesktopEntry() throws IOException {
        String input = "" +
                "[Desktop Entry]\n" +
                "Version=1.0\n" +
                "Type=Application\n" +
                "Name=Foo Viewer\n" +
                "Name[en_CA]=en_CA Foo Viewer\n" +
                "NAME=Not the actual name - ignored\n" +
                "Comment=The best viewer for Foo objects available!\n" +
                "TryExec=fooview\n" +
                "Exec=fooview %F\n" +
                "Icon=fooview\n" +
                "MimeType=image/x-foo;\n" +
                "X-KDE-Library=libfooview\n" +
                "X-KDE-FactoryName=fooviewfactory\n" +
                "X-KDE-ServiceType=FooService\n" +
                "[Another Group]\n" +
                "Version=1.1\n" +
                "Icon=icon\n";
        DesktopEntry entry = new DesktopEntry(new IniStyleFileReader(new BufferedReader(new StringReader(input))).read());
        return entry;
    }

    @Test
    public void testBasicParsing() throws IOException {
        DesktopEntry entry = createBasicDesktopEntry();
        assertTrue(entry.containsGroup(DesktopEntry.GROUP_REQUIRED));

        assertTrue(entry.containsKey(DesktopEntry.KEY_VERSION));
        assertEquals("1.0", entry.get(DesktopEntry.KEY_VERSION));
        assertTrue(entry.containsKey(DesktopEntry.KEY_TYPE));
        assertEquals("Application", entry.get(DesktopEntry.KEY_TYPE));
        assertTrue(entry.containsKey(DesktopEntry.KEY_NAME));
        assertEquals("Foo Viewer", entry.get(DesktopEntry.KEY_NAME));

        assertTrue(entry.containsKey(DesktopEntry.KEY_COMMENT));

        assertTrue(entry.containsKey(DesktopEntry.KEY_EXEC));
    }

    @Test
    public void testExtensionsPresent() throws IOException {
        DesktopEntry entry = createBasicDesktopEntry();
        assertTrue(entry.containsKey(DesktopEntry.GROUP_REQUIRED, "X-KDE-Library"));
        assertTrue(entry.containsKey(DesktopEntry.GROUP_REQUIRED, "X-KDE-FactoryName"));
        assertTrue(entry.containsKey(DesktopEntry.GROUP_REQUIRED, "X-KDE-ServiceType"));
    }

    @Test
    public void testLocalizedNames() throws IOException {
        DesktopEntry entry = createBasicDesktopEntry();
        String value;
        value = entry.getLocalizedValue(DesktopEntry.KEY_NAME, new Locale("en", "CA"));
        assertEquals("en_CA Foo Viewer", value);

        value = entry.getLocalizedValue(DesktopEntry.KEY_NAME, new Locale("en"));
        assertEquals("Foo Viewer", value);
    }
}
