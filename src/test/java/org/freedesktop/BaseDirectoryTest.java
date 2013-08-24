package org.freedesktop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseDirectoryTest {

    private Map<String, String> buildCustomEnvironment() {
        Map<String, String> environment = new HashMap<String, String>();
        environment.put("HOME", "${HOME}");
        return environment;
    }

    @Before
    public void setUp() {
        BaseDirectory.setEnvironment(System.getenv());
    }

    @After
    public void tearDown() {
        BaseDirectory.setEnvironment(System.getenv());
    }

    @Test
    public void testUnknown() {
        String unknownVar = BaseDirectory.get("UNKNOWN_AND_IMPOSSIBLE_XDG_VARIABLE");
        assertEquals(null, unknownVar);
    }

    @Test
    public void testDataHomeWithEnvSet() {
        Map<String, String> env = buildCustomEnvironment();
        env.put("XDG_DATA_HOME", "${XDG_DATA_HOME}");
        BaseDirectory.setEnvironment(env);
        String dataHome = BaseDirectory.get(BaseDirectory.XDG_DATA_HOME);
        assertNotNull(dataHome);
        assertEquals("${XDG_DATA_HOME}", dataHome);
    }

    @Test
    public void testDataHomeDefault() {
        BaseDirectory.setEnvironment(buildCustomEnvironment());
        String dataHome = BaseDirectory.get(BaseDirectory.XDG_DATA_HOME);
        assertNotNull(dataHome);
        assertEquals("${HOME}/.local/share", dataHome);
    }

    @Test
    public void testConfigHomeWithEnvSet() {
        Map<String, String> env = buildCustomEnvironment();
        env.put("XDG_CONFIG_DIRS", "${XDG_CONFIG_DIRS}");
        BaseDirectory.setEnvironment(env);
        String dir = BaseDirectory.get(BaseDirectory.XDG_CONFIG_DIRS);
        assertNotNull(dir);
        assertEquals("${XDG_CONFIG_DIRS}", dir);
    }

    @Test
    public void testConfigHomeDefault() {
        BaseDirectory.setEnvironment(buildCustomEnvironment());
        String configHome = BaseDirectory.get(BaseDirectory.XDG_CONFIG_HOME);
        assertNotNull(configHome);
        assertEquals("${HOME}/.config", configHome);
    }

    @Test
    public void testDataDirsWithEnvSet() {
        Map<String, String> env = buildCustomEnvironment();
        env.put("XDG_DATA_DIRS", "${XDG_DATA_DIRS}");
        BaseDirectory.setEnvironment(env);
        String dataDirs = BaseDirectory.get(BaseDirectory.XDG_DATA_DIRS);
        assertNotNull(dataDirs);
        assertEquals("${XDG_DATA_DIRS}", dataDirs);
    }

    @Test
    public void testDataDirsDefault() {
        BaseDirectory.setEnvironment(buildCustomEnvironment());
        String dataDirs = BaseDirectory.get(BaseDirectory.XDG_DATA_DIRS);
        assertNotNull(dataDirs);
        assertEquals("/usr/local/share/:/usr/share/", dataDirs);
    }

    @Test
    public void testConfigDirsWithEnvSet() {
        Map<String, String> env = buildCustomEnvironment();
        env.put("XDG_CONFIG_DIRS", "${XDG_CONFIG_DIRS}");
        BaseDirectory.setEnvironment(env);
        String configDirs = BaseDirectory.get(BaseDirectory.XDG_CONFIG_DIRS);
        assertNotNull(configDirs);
        assertEquals("${XDG_CONFIG_DIRS}", configDirs);
    }

    @Test
    public void testConfigDirsDefault() {
        BaseDirectory.setEnvironment(buildCustomEnvironment());
        String configDirs = BaseDirectory.get(BaseDirectory.XDG_CONFIG_DIRS);
        assertNotNull(configDirs);
        assertEquals("/etc/xdg", configDirs);

    }

    @Test
    public void testCacheHomeWithEnvSet() {
        Map<String, String> env = buildCustomEnvironment();
        env.put("XDG_CACHE_HOME", "${XDG_CACHE_HOME}");
        BaseDirectory.setEnvironment(env);
        String dir = BaseDirectory.get(BaseDirectory.XDG_CACHE_HOME);
        assertNotNull(dir);
        assertEquals("${XDG_CACHE_HOME}", dir);
    }

    @Test
    public void testCacheHomeDefault() {
        BaseDirectory.setEnvironment(buildCustomEnvironment());
        String dir = BaseDirectory.get(BaseDirectory.XDG_CACHE_HOME);
        assertNotNull(dir);
        assertEquals("${HOME}/.cache", dir);
    }

    @Test
    public void testRuntimeDirWithEnvSet() {
        Map<String, String> env = buildCustomEnvironment();
        env.put("XDG_RUNTIME_DIR", "${XDG_RUNTIME_DIR}");
        BaseDirectory.setEnvironment(env);
        String runtimeDir = BaseDirectory.get(BaseDirectory.XDG_RUNTIME_DIR);
        assertNotNull(runtimeDir);
        assertEquals("${XDG_RUNTIME_DIR}", runtimeDir);
    }

    @Test
    public void testRuntimeDirWithoutEnvSet() {
        BaseDirectory.setEnvironment(buildCustomEnvironment());
        String runtimeDir = BaseDirectory.get(BaseDirectory.XDG_RUNTIME_DIR);
        assertEquals(null, runtimeDir);
    }

    @Test
    public void testRuntimeDirMatchesSystemEnv() {
        BaseDirectory.setEnvironment(System.getenv());
        String runtimeDir = BaseDirectory.get(BaseDirectory.XDG_RUNTIME_DIR);
        assertEquals(System.getenv("XDG_RUNTIME_DIR"), runtimeDir);
    }

}
