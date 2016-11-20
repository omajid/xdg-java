package org.freedesktop.platforms;

import java.io.File;

import org.freedesktop.Platform;

public class Windows extends Platform {

	@Override
	public String getCacheHome() {
		return environment.get("LOCALAPPDATA");
	}

	@Override
	public String getConfigDirs() {
		return environment.get("APPDATA") + File.pathSeparator + environment.get("LOCALAPPDATA");
	}

	@Override
	public String getConfigHome() {
		return environment.get("APPDATA");
	}

	@Override
	public String getDataDirs() {
		return environment.get("APPDATA") + File.pathSeparator + environment.get("LOCALAPPDATA");
	}

	@Override
	public String getDataHome() {
		return environment.get("APPDATA");
	}

	@Override
	public String getRuntimeDir() {
		return environment.get("LOCALAPPDATA") + File.separator + "Temp";
	}

}
