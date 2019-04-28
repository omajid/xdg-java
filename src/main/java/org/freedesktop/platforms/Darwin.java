package org.freedesktop.platforms;

import org.freedesktop.Platform;

public class Darwin extends Platform {

	@Override
	public String getCacheHome() {
		return environment.get("HOME") + "/Library/Caches";
	}

	@Override
	public String getConfigDirs() {
		return "/Library/Preferences;/Library/Application Support";
	}

	@Override
	public String getConfigHome() {
		return environment.get("HOME") + "/Library/Preferences";
	}

	@Override
	public String getDataDirs() {
		return "/Library";
	}

	@Override
	public String getDataHome() {
		return environment.get("HOME") + "/Library";
	}

	@Override
	public String getRuntimeDir() {
		return environment.get("TMPDIR");
	}

}
