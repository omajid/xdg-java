package org.freedesktop;

import java.util.Map;

import org.freedesktop.platforms.Darwin;
import org.freedesktop.platforms.Default;
import org.freedesktop.platforms.Windows;

public abstract class Platform {

    protected static Map<String,String> environment = System.getenv();
    
	public abstract String getCacheHome();
	public abstract String getConfigDirs();
	public abstract String getConfigHome();
	public abstract String getDataDirs();
	public abstract String getDataHome();
	public abstract String getRuntimeDir();
	
	public static Platform getCurrent() {
		
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Mac OS X")) {
			return new Darwin();
		} else if (osName.startsWith("Windows")) {
			return new Windows();
		}
		
		return new Default();
	}
}
