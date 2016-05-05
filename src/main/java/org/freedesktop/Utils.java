package org.freedesktop;

import java.io.File;
import java.io.IOException;

class Utils {

    public static void deleteRecursively(File file) throws IOException {
        if (file.isDirectory()) {
            for (File children : file.listFiles()) {
                deleteRecursively(file);
            }
        }

        if (!file.delete()) {
            throw new IOException("Unable to delete file");
        }
    }


}
