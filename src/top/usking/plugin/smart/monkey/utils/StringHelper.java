package top.usking.plugin.smart.monkey.utils;

import java.io.File;

/**
 * String 工具类.
 */
public abstract class StringHelper {

    /**
     * 获得文件名.
     *
     * @param fullPath 文件全路径.
     * @return 文件名.
     */
    public static String getFilename(String fullPath) {
        if (fullPath == null) {
            return null;
        }
        int separatorIndex = fullPath.lastIndexOf(File.separator);
        return (separatorIndex != -1 ? fullPath.substring(separatorIndex + 1) : fullPath);
    }
}
