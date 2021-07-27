package top.usking.plugin.smart.monkey.utils;

import java.io.File;

public class StringHelper {

    private static final String FOLDER_SEPARATOR = "/";

    public static String getFilename(String path) {
        if (path == null) {
            return null;
        }

        int separatorIndex = path.lastIndexOf(File.separator);
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
    }
}
