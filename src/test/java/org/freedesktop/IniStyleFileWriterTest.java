package org.freedesktop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

public class IniStyleFileWriterTest {

    @Test
    public void testSimpleWriting() throws IOException {
        StringWriter w = new StringWriter();
        IniStyleFileWriter writer = new IniStyleFileWriter(w);
        writer.write(new IniStyleFile("temp"));
        String result = w.getBuffer().toString();
        assertNotNull(result);
        assertEquals("", result);
    }

    @Test
    public void testWritingWithOneGroup() throws IOException {
        StringWriter w = new StringWriter();
        IniStyleFileWriter writer = new IniStyleFileWriter(w);
        IniStyleFile file = new IniStyleFile("group");
        file.add("key", "value");
        writer.write(file);
        String result = w.getBuffer().toString();
        assertNotNull(result);
        assertEquals("[group]\nkey=value\n", result);
    }

    @Test
    public void testWritingWithOneGroupReplacingExistingKeys() throws IOException {
        StringWriter w = new StringWriter();
        IniStyleFileWriter writer = new IniStyleFileWriter(w);
        IniStyleFile file = new IniStyleFile("group");
        file.add("key", "value");
        file.add("key", "new-value");
        writer.write(file);
        String result = w.getBuffer().toString();
        assertNotNull(result);
        assertEquals("[group]\nkey=new-value\n", result);
    }

}
