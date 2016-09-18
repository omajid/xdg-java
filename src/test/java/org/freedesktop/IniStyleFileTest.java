package org.freedesktop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class IniStyleFileTest {

    @Test
    public void testConstructor() {
        IniStyleFile entry = new IniStyleFile();
        assertNotNull(entry);
    }

    @Test
    public void testConstructor2() {
        IniStyleFile file1 = new IniStyleFile();
        file1.add("test", "test", "test");

        IniStyleFile file2 = new IniStyleFile(file1);
        assertTrue(file2.containsKey("test", "test"));
        assertEquals("test", file2.get("test", "test"));
    }

    @Test
    public void testWithDefaultGroup() {
        IniStyleFile file = new IniStyleFile("default-group");
        file.add("key", "value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullGroupWithKeyValue() {
        IniStyleFile file = new IniStyleFile();
        file.add(null, "key", "value");
    }

    /*
     * Add group
     */

    @Test
    public void testAddGroup() {
        IniStyleFile file = new IniStyleFile();
        file.addGroup("test");
        assertTrue(file.containsGroup("test"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGroupWithNewLine() {
        IniStyleFile file = new IniStyleFile();
        file.addGroup("test\nandmore");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullGroup() {
        IniStyleFile file = new IniStyleFile();
        file.addGroup(null);
    }

    @Test
    public void testAddGroupMultipleTimes() {
        IniStyleFile file = new IniStyleFile();
        file.addGroup("test");
        assertTrue(file.containsGroup("test"));
        file.addGroup("test");
        assertTrue(file.containsGroup("test"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidGroupName1() {
        IniStyleFile entry = new IniStyleFile();
        entry.add("[", "foo", "bar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidGroupName2() {
        IniStyleFile entry = new IniStyleFile();
        entry.add("]", "foo", "bar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidGroupName3() {
        IniStyleFile entry = new IniStyleFile();
        entry.add(" ", "foo", "bar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidGroupName4() {
        IniStyleFile file = new IniStyleFile();
        file.add("\n", "foo", "bar");
    }

    /*
     * Contains group
     */

    @Test
    public void testContainsGroup() {
        IniStyleFile file = new IniStyleFile("default-group");
        file.add("key", "value");
        assertTrue(file.containsGroup("default-group"));
    }

    @Test
    public void testContainsNonExistingGroup() {
        IniStyleFile file = new IniStyleFile("default-group");
        assertFalse(file.containsGroup("some-other-group"));
    }

    /*
     * Get group
     */

    @Test
    public void testGetGroup() {
        IniStyleFile file = new IniStyleFile("default-group");
        file.add("key", "value");
        Map<String, String> group = file.getGroup("default-group");
        assertEquals(1, group.entrySet().size());
    }

    @Test
    public void testGetGroupMissing() {
        IniStyleFile file = new IniStyleFile("default-group");
        Map<String, String> group = file.getGroup("some-random-group");
        assertEquals(null, group);
    }

    @Test
    public void testGetDefaultGroupName() {
        IniStyleFile file = new IniStyleFile("default-group");
        String groupName = file.getDefaultGroupName();
        assertEquals("default-group", groupName);
    }

    @Test
    public void testGetDefaultGroupMissing() {
        IniStyleFile file = new IniStyleFile("default-group");
        String defaultGroup = file.getDefaultGroupName();
        Map<String, String> group = file.getGroup(defaultGroup);
        assertEquals(null, group);
    }

    @Test
    public void testGetGroupNames() {
        IniStyleFile file = new IniStyleFile("default-group");
        file.add("key", "value");
        Set<String> groupNames = file.getGroupNames();
        assertFalse(groupNames.isEmpty());
        assertEquals(1, groupNames.size());
        assertEquals("default-group", groupNames.toArray(new String[0])[0]);
    }

    /*
     * Remove group
     */

    @Test
    public void testRemoveGroup() {
        IniStyleFile file = new IniStyleFile("default-group");
        file.add("new-group", "key", "value");
        assertTrue(file.containsKey("new-group", "key"));
        file.removeGroup("new-group");
        assertFalse(file.containsGroup("new-group"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullGroup() {
        IniStyleFile file = new IniStyleFile("default-group");
        assertFalse(file.containsGroup(null));
        file.removeGroup(null);
    }

    /*
     * Add key/value
     */

    @Test
    public void testAddKeyValue() {
        IniStyleFile file = new IniStyleFile("default-group");
        file.add("key", "value");
        assertEquals("value", file.get("default-group", "key"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidKeyName() {
        IniStyleFile file = new IniStyleFile();
        file.add("test", "=", "value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmptyKeyName() {
        IniStyleFile file = new IniStyleFile();
        file.add("test", "", "value");
    }

    @Test
    public void testAddEmptyValue() {
        IniStyleFile file = new IniStyleFile();
        file.add("test", "key", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullKeyName() {
        IniStyleFile file = new IniStyleFile();
        file.add(null, "dummy");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewLines1() {
        IniStyleFile entry = new IniStyleFile();
        entry.add("\n", "value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewLines2() {
        IniStyleFile entry = new IniStyleFile();
        entry.add("key", "\n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewLines3() {
        IniStyleFile entry = new IniStyleFile();
        entry.add("key\nkey", "value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewLines4() {
        IniStyleFile entry = new IniStyleFile();
        entry.add("key", "value\nvalue");
    }

    @Test
    public void testAddingKnownLocaleStringValueType() {
        IniStyleFile entry = new IniStyleFile();
        entry.addKnownType("FOO", "BAR", IniStyleFile.ValueType.LOCALE_STRING);
        entry.add("FOO", "BAR", "[en_US]");
    }

    @Test
    public void testAddingKnownStringsValueType() {
        IniStyleFile entry = new IniStyleFile();
        entry.addKnownType("FOO", "BAR", IniStyleFile.ValueType.STRINGS);
        entry.add("FOO", "BAR", "GNOME:UNITY");
    }

    @Test
    public void testAddingKnownStringValueType() {
        IniStyleFile entry = new IniStyleFile();
        entry.addKnownType("FOO", "BAR", IniStyleFile.ValueType.STRING);
        entry.add("FOO", "BAR", "foo");
    }

    // TODO
    // @Test
    // public void testAddingKnownIntegersValueType() {
    // }

    // TODO
    // @Test(expected = IllegalArgumentException.class)
    // public void testKnownValueTypeRejectsBadIntegersValueType() {
    // }

    @Test
    public void testAddingKnownIntegerValueType() {
        IniStyleFile entry = new IniStyleFile();
        entry.addKnownType("FOO", "BAR", IniStyleFile.ValueType.INTEGER);
        entry.add("FOO", "BAR", "10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKnownValueTypeRejectsBadIntegerValueType() {
        IniStyleFile entry = new IniStyleFile();
        entry.addKnownType("FOO", "BAR", IniStyleFile.ValueType.INTEGER);
        entry.add("FOO", "BAR", "ten");
    }

    @Test
    public void testAddingKnownBooleanValueType() {
        IniStyleFile entry = new IniStyleFile();
        entry.addKnownType("FOO", "BAR", IniStyleFile.ValueType.BOOLEAN);
        entry.add("FOO", "BAR", "true");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKnownValueTypeRejectsBadBooleanValueType() {
        IniStyleFile entry = new IniStyleFile();
        entry.addKnownType("FOO", "BAR", IniStyleFile.ValueType.BOOLEAN);
        entry.add("FOO", "BAR", "falsy");
    }

    // TODO
    // @Test
    // public void testAddingKnownPointsValueType() {
    // }

    // TODO
    // @Test(expected = IllegalArgumentException.class)
    // public void testKnownValueTypeRejectsBadPointsValueType() {
    // }

    /*
     * Contains key/value
     */

    @Test
    public void testContainsKeyValue() {
        IniStyleFile file = new IniStyleFile("default-group");
        file.add("key", "value");
        assertTrue(file.containsKey("default-group", "key"));
    }

    @Test
    public void testContainsMissingKey() {
        IniStyleFile file = new IniStyleFile("default-group");
        assertFalse(file.containsGroup(null));
        assertFalse(file.containsKey("group", "key"));
    }

    /*
     * Get key/value
     */

    @Test
    public void testGetKeyValueFromDefaultGroup() {
        IniStyleFile file = new IniStyleFile("default-group");
        file.add("key", "value");
        assertEquals("value", file.get("key"));
    }

    @Test
    public void testGetNonExistingKeyValueFromDefaultGroup() {
        IniStyleFile file = new IniStyleFile("default-group");
        assertEquals(null, file.get("key"));
    }

    @Test
    public void testGetKeyValueFromGroup() {
        IniStyleFile file = new IniStyleFile();
        file.add("group", "key", "value");
        assertEquals("value", file.get("group", "key"));
    }

    @Test
    public void testGetNonExistingKeyValueFromGroup() {
        IniStyleFile file = new IniStyleFile();
        assertEquals(null, file.get("group", "key"));
    }

    /*
     * Remove key/value
     */

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingKey() {
        IniStyleFile file = new IniStyleFile();
        file.remove("some-non-existing-key");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingKey2() {
        IniStyleFile file = new IniStyleFile("default-group");
        file.remove("default-group", "some-non-existing-key");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingKey3() {
        IniStyleFile file = new IniStyleFile("default-group");
        file.add("default-group", "another-key", "another-value");
        file.remove("default-group", "some-non-existing-key");
    }

    @Test
    public void testRemoveExistingKey() {
        IniStyleFile file = new IniStyleFile();
        file.add("key", "value");
        assertTrue(file.containsKey("key"));
        file.remove("key");
        assertFalse(file.containsKey("key"));
    }
}
