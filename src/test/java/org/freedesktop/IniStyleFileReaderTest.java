package org.freedesktop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class IniStyleFileReaderTest {

    @Test
    public void testMinimalRead() throws IOException {
        String input = "[Desktop Entry]\n" +
                "Name=Foo\n";
        StringReader reader = new StringReader(input);
        IniStyleFileReader fileReader = new IniStyleFileReader(new BufferedReader(reader));
        IniStyleFile file = fileReader.read();
        assertNotNull(file);

        // check groups
        assertFalse(file.getGroupNames().isEmpty());
        assertEquals(1, file.getGroupNames().size());
        assertEquals("Desktop Entry", file.getGroupNames().toArray(new String[0])[0]);

        // check default group
        assertFalse(file.containsKey("Name"));
        assertEquals(null, file.get("Name"));

        // check group-values
        assertTrue(file.containsGroup("Desktop Entry"));
        assertTrue(file.containsKey("Desktop Entry", "Name"));
        assertEquals("Foo", file.get("Desktop Entry", "Name"));
    }

    @Test
    public void testBasicValidInput() throws IOException {
        String input = "" +
                "[Desktop Entry]\n" +
                "Version=1.0\n" +
                "Type=Application\n" +
                "Name=Foo Viewer\n" +
                "Comment=The best viewer for Foo objects available!\n" +
                "TryExec=fooview\n" +
                "Exec=fooview %F\n" +
                "Icon=fooview\n" +
                "MimeType=image/x-foo;\n" +
                "X-KDE-Library=libfooview\n" +
                "X-KDE-FactoryName=fooviewfactory\n" +
                "X-KDE-ServiceType=FooService\n";
        StringReader reader = new StringReader(input);
        IniStyleFileReader fileReader = new IniStyleFileReader(new BufferedReader(reader));
        IniStyleFile file = fileReader.read();
        assertNotNull(file);

    }
}
