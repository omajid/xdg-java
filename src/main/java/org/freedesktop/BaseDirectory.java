package org.freedesktop;

import java.io.File;
import java.util.Map;

/**
 * A simple way to utilize the Base Directory Specification
 *
 * @version 0.7
 */
public class BaseDirectory {

    /**
     * base directory relative to which user-specific non-essential (cached)
     * data should be written
     */
    public static final String XDG_CACHE_HOME = "XDG_CACHE_HOME";

    /**
     * base directory relative to which user-specific configuration files should
     * be written
     */
    public static final String XDG_CONFIG_HOME = "XDG_CONFIG_HOME";

    /**
     * set of preference ordered base directories relative to which
     * configuration files should be searched. The directories are separated by
     * by {@link File#pathSeparator}
     */
    public static final String XDG_CONFIG_DIRS = "XDG_CONFIG_DIRS";

    /**
     * base directory relative to which user-specific data files should be
     * written
     */
    public static final String XDG_DATA_HOME = "XDG_DATA_HOME";

    /**
     * set of preference ordered base directories relative to which data files
     * should be searched. Directories are separated by {@link File#pathSeparator}
     */
    public static final String XDG_DATA_DIRS = "XDG_DATA_DIRS";

    /**
     * base directory relative to which user-specific runtime files and other
     * file objects should be placed. May be {@code null}.
     */
    public static final String XDG_RUNTIME_DIR = "XDG_RUNTIME_DIR";

    static Map<String,String> environment = Platform.environment;

    /**
     * Get the base directory or set of base directories defined by the
     * specification.
     *
     * @param name one of the XDG_* constants defined in {@link BaseDirectory}
     * @return the directory (or the set of directories) as a string. For the
     * exact return type, see the docs for the constant. May be null if the
     * standard does not specify a default and the associated environment
     * variable(s) are undefined.
     */
    public static String get(String name) {
        switch (name) {
            case XDG_CACHE_HOME:
                return getCacheHome();
            case XDG_CONFIG_HOME:
                return getConfigHome();
            case XDG_CONFIG_DIRS:
                return getConfigDirs();
            case XDG_DATA_HOME:
                return getDataHome();
            case XDG_DATA_DIRS:
                return getDataDirs();
            case XDG_RUNTIME_DIR:
                return getRuntimeDir();
            default:
                return null;
        }
    }

    private static String getCacheHome() {
        String value = environment.get(XDG_CACHE_HOME);
        if (value == null || value.trim().length() == 0) {
            String XDG_CACHE_HOME_DEFAULT = Platform.getCurrent().getCacheHome();
            value = XDG_CACHE_HOME_DEFAULT;
        }
        return value;
    }

    private static String getConfigHome() {
        String value = environment.get(XDG_CONFIG_HOME);
        if (value == null || value.trim().length() == 0) {
            String XDG_CONFIG_HOME_DEFAULT = Platform.getCurrent().getConfigHome();
            value = XDG_CONFIG_HOME_DEFAULT;
        }
        return value;
    }

    private static String getConfigDirs() {
        String value = environment.get(XDG_CONFIG_DIRS);
        if (value == null || value.trim().length() == 0) {
            String XDG_CONFIG_DIRS_DEFAULT = Platform.getCurrent().getConfigDirs();
            value = XDG_CONFIG_DIRS_DEFAULT;
        }
        return value;
    }

    private static String getDataHome() {
        String value = environment.get(XDG_DATA_HOME);
        if (value == null || value.trim().length() == 0) {
            String XDG_DATA_HOME_DEFAULT = Platform.getCurrent().getDataHome();
            value = XDG_DATA_HOME_DEFAULT;
        }
        return value;
    }

    private static String getDataDirs() {
        String value = environment.get(XDG_DATA_DIRS);
        if (value == null || value.trim().length() == 0) {
            String XDG_DATA_DIRS_DEFAULT = Platform.getCurrent().getDataDirs();
            value = XDG_DATA_DIRS_DEFAULT;
        }
        return value;
    }

    private static String getRuntimeDir() {
        String value = environment.get(XDG_RUNTIME_DIR);
        if (value == null || value.trim().length() == 0) {
            String XDG_RUNTIME_DIR_DEFAULT = Platform.getCurrent().getRuntimeDir();
            value = XDG_RUNTIME_DIR_DEFAULT;
        }
        return value;
    }

}
