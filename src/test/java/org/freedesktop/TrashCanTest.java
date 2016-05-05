
package org.freedesktop;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TrashCanTest {

    private TrashCan trashCan;
    private Path trashRoot;

    @Before
    public void initializeTrash() throws Exception {
        trashRoot = Files.createTempDirectory("xdg-java-trash");
        trashCan = new TrashCan(trashRoot.toFile());
    }

    @After
    public void cleanupTrash() throws Exception {
        Utils.deleteRecursively(trashRoot.toFile());
    }

    @Test
    public void testEmptyHomeTrash() {
        assertEquals(trashRoot.toFile(), trashCan.getHomeTrash());
        assertTrue(trashCan.getTrashedFiles().isEmpty());
    }

    @Test
    public void testTrashSingleFile() throws Exception {
        assertTrue(trashCan.getTrashedFiles().isEmpty());

        File toTrash = File.createTempFile("xdg-java-single-file-to-trash", null);
        assertTrue(toTrash.isFile());

        trashCan.trash(toTrash);

        assertFalse(toTrash.isFile());
        assertFalse(trashCan.getTrashedFiles().isEmpty());
    }
}
