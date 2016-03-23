package org.freedesktop;

import java.util.Locale;

/**
 * An interface to the Desktop Entry Specification.
 * 
 * @version 1.1
 */
public class DesktopEntry extends IniStyleFile {

    public static final String EXTENSION_FILE = ".desktop";
    public static final String EXTENSION_DIRECTORY = ".directory";
    public static final String FILE_CHARSET = "UTF-8";

    public static final String GROUP_REQUIRED = "Desktop Entry";
    public static final String GROUP_DEFAULT = GROUP_REQUIRED;

    public static final String KEY_TYPE = "Type";
    public static final String KEY_VERSION = "Version";
    public static final String KEY_NAME = "Name";
    public static final String KEY_GENERIC_NAME = "GenericName";
    public static final String KEY_NO_DISPLAY = "NoDisplay";
    public static final String KEY_COMMENT = "Comment";
    public static final String KEY_ICON = "Icon";
    public static final String KEY_HIDDEN = "Hidden";
    public static final String KEY_ONLY_SHOW_IN = "OnlyShowIn";
    public static final String KEY_NOT_SHOW_IN = "NotShowIn";
    public static final String KEY_DBUS_ACTIVATABLE = "DBusActivatable";
    public static final String KEY_TRY_EXEC = "TryExec";
    public static final String KEY_EXEC = "Exec";
    public static final String KEY_PATH = "Path";
    public static final String KEY_TERMINAL = "Terminal";
    public static final String KEY_ACTIONS = "Actions";
    public static final String KEY_MIME_TYPE = "MimeType";
    public static final String KEY_CATEGORIES = "Categories";
    public static final String KEY_KEYWORDS = "Keywords";
    public static final String KEY_STARTUP_NOTIFY = "StartupNotify";
    public static final String KEY_STARTUP_WM_CLASS = "StartupWMClass";
    public static final String KEY_URL = "URL";

    public DesktopEntry() {
        super(GROUP_DEFAULT);

        initKnownTypes();
    }

    public DesktopEntry(IniStyleFile source) {
        super(source, GROUP_DEFAULT);

        initKnownTypes();
    }

    private void initKnownTypes() {
        addKnownType(GROUP_DEFAULT, KEY_TYPE, ValueType.STRING);
        addKnownType(GROUP_DEFAULT, KEY_VERSION, ValueType.STRING);
        addKnownType(GROUP_DEFAULT, KEY_NAME, ValueType.LOCALE_STRING);
        addKnownType(GROUP_DEFAULT, KEY_GENERIC_NAME, ValueType.LOCALE_STRING);
        addKnownType(GROUP_DEFAULT, KEY_NO_DISPLAY, ValueType.BOOLEAN);
        addKnownType(GROUP_DEFAULT, KEY_COMMENT, ValueType.LOCALE_STRING);
        addKnownType(GROUP_DEFAULT, KEY_ICON, ValueType.LOCALE_STRING);
        addKnownType(GROUP_DEFAULT, KEY_HIDDEN, ValueType.BOOLEAN);
        addKnownType(GROUP_DEFAULT, KEY_ONLY_SHOW_IN, ValueType.STRINGS);
        addKnownType(GROUP_DEFAULT, KEY_NOT_SHOW_IN, ValueType.STRINGS);
        addKnownType(GROUP_DEFAULT, KEY_DBUS_ACTIVATABLE, ValueType.BOOLEAN);
        addKnownType(GROUP_DEFAULT, KEY_TRY_EXEC, ValueType.STRING);
        addKnownType(GROUP_DEFAULT, KEY_EXEC, ValueType.STRING);
        addKnownType(GROUP_DEFAULT, KEY_PATH, ValueType.STRING);
        addKnownType(GROUP_DEFAULT, KEY_TERMINAL, ValueType.BOOLEAN);
        addKnownType(GROUP_DEFAULT, KEY_ACTIONS, ValueType.STRINGS);
        addKnownType(GROUP_DEFAULT, KEY_MIME_TYPE, ValueType.STRINGS);
        addKnownType(GROUP_DEFAULT, KEY_CATEGORIES, ValueType.STRINGS);
        addKnownType(GROUP_DEFAULT, KEY_KEYWORDS, ValueType.LOCALE_STRING);
        addKnownType(GROUP_DEFAULT, KEY_STARTUP_NOTIFY, ValueType.BOOLEAN);
        addKnownType(GROUP_DEFAULT, KEY_STARTUP_WM_CLASS, ValueType.STRING);
        addKnownType(GROUP_DEFAULT, KEY_URL, ValueType.STRING);
    }

    public String getLocalizedValue(String key, Locale locale) {
        return getLocalizedValue(defaultGroup, key, locale);
    }

    public String getLocalizedValue(String group, String key, Locale locale) {
        String suffix = locale.getLanguage();
        if (!locale.getCountry().equals("")) {
            suffix = suffix + "_" + locale.getCountry();
        }
        String result;

        result = get(group, key + "[" + suffix + "]");
        if (result != null) {
            return result;
        }

        suffix = locale.getLanguage();
        result = get(group, key + "[" + suffix + "]");
        if (result != null) {
            return result;
        }

        result = get(group, key);
        return result;
    }

    @Override
    protected void checkAllValid() {
        super.checkAllValid();
        // TODO add more
    }

    @Override
    protected void checkValidKeyValue(String group, String key, String value) {
        super.checkValidKeyValue(group, key, value);

        if (group.equals(GROUP_DEFAULT) && key.equals(KEY_EXEC)) {
            checkValidExecValue(value);
        }
    }

    private void checkValidExecValue(String value) {
        // TODO implement
    }

}
