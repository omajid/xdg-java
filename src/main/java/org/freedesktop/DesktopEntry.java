package org.freedesktop;

/**
 * An interface to the Desktop Entry Specification.
 * 
 * @version 1.1
 */
public class DesktopEntry extends IniStyleFile {

	public static final String FILE_EXTENSION_FILE = ".desktop";
	public static final String FILE_EXTENSION_DIRECTORY = ".directory";
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
		knownTypes.put(KEY_TYPE, ValueType.STRING);
		knownTypes.put(KEY_VERSION, ValueType.STRING);
		knownTypes.put(KEY_NAME, ValueType.LOCALE_STRING);
		knownTypes.put(KEY_GENERIC_NAME, ValueType.LOCALE_STRING);
		knownTypes.put(KEY_NO_DISPLAY, ValueType.BOOLEAN);
		knownTypes.put(KEY_COMMENT, ValueType.LOCALE_STRING);
		knownTypes.put(KEY_ICON, ValueType.LOCALE_STRING);
		knownTypes.put(KEY_HIDDEN, ValueType.BOOLEAN);
		knownTypes.put(KEY_ONLY_SHOW_IN, ValueType.STRINGS);
		knownTypes.put(KEY_NOT_SHOW_IN, ValueType.STRINGS);
		knownTypes.put(KEY_DBUS_ACTIVATABLE, ValueType.BOOLEAN);
		knownTypes.put(KEY_TRY_EXEC, ValueType.STRING);
		knownTypes.put(KEY_EXEC, ValueType.STRING);
		knownTypes.put(KEY_PATH, ValueType.STRING);
		knownTypes.put(KEY_TERMINAL, ValueType.BOOLEAN);
		knownTypes.put(KEY_ACTIONS, ValueType.STRINGS);
		knownTypes.put(KEY_MIME_TYPE, ValueType.STRINGS);
		knownTypes.put(KEY_CATEGORIES, ValueType.STRINGS);
		knownTypes.put(KEY_KEYWORDS, ValueType.LOCALE_STRING);
		knownTypes.put(KEY_STARTUP_NOTIFY, ValueType.BOOLEAN);
		knownTypes.put(KEY_STARTUP_WM_CLASS, ValueType.STRING);
		knownTypes.put(KEY_URL, ValueType.STRING);
	}

	@Override
	protected void checkAllValid() {
		super.checkAllValid();
		// TODO add more
	}

	@Override
	protected void checkValidKeyValue(String key, String value) {
		super.checkValidKeyValue(key, value);

		if (key.equals(KEY_EXEC)) {
			checkValidExecValue(value);
		}
	}

	private void checkValidExecValue(String value) {
		// TODO implement
	}

}
