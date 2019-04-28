package org.freedesktop.platforms;

import java.io.File;

import org.freedesktop.Platform;

public class Default extends Platform {

	@Override
	public String getCacheHome() {
		return environment.get("HOME") + File.separator + ".cache";
	}

	@Override
	public String getConfigDirs() {
		return File.separator + "etc" + File.separator + "xdg";
	}

	@Override
	public String getConfigHome() {
		return environment.get("HOME") + File.separator + ".config";
	}

	@Override
	public String getDataDirs() {
		String XDG_DATA_DIRS_DEFAULT = File.separator + "usr" + File.separator + "local" + File.separator + "share" + File.separator;
        XDG_DATA_DIRS_DEFAULT = XDG_DATA_DIRS_DEFAULT + File.pathSeparator;
        XDG_DATA_DIRS_DEFAULT = XDG_DATA_DIRS_DEFAULT + File.separator + "usr" + File.separator + "share" + File.separator;
        return XDG_DATA_DIRS_DEFAULT;
	}

	@Override
	public String getDataHome() {
		return environment.get("HOME") + File.separator + ".local" + File.separator + "share";
	}

	@Override
	public String getRuntimeDir() {
		return null;
	}

}
