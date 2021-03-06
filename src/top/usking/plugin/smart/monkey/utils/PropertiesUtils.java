package top.usking.plugin.smart.monkey.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.*;
import java.util.*;

/**
 * 配置文件工具类.
 */
public abstract class PropertiesUtils {

    public static String[] getPaths(String basePath, String pathContent) {
        if (Objects.isNull(pathContent)) {
            return new String[]{basePath};
        }
        String[] split = pathContent.split(",");
        for (int i = 0; i < split.length; i++) {
            split[i] = basePath + split[i];
        }
        return split;
    }

    public static String getPropertyValue(String text, String key) {
        try {
            InputStream in =new ByteArrayInputStream(text.getBytes("UTF-8"));
            Properties prop = new Properties();
            prop.load(in);
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getAllFiles(String... paths) {
        List<String> collect = new ArrayList<>();
        for (String path : paths) {
            File file = new File(path);
            if (file.exists()) {
                if (file.isDirectory()) {
                    Collection<File> listFiles = FileUtils.listFiles(new File(path), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

                    for (File f : listFiles) {
                        if (f.isFile()) {
                            collect.add(f.getPath());
                        }
                    }
                } else if (file.isFile()) {
                    collect.add(file.getPath());
                }

            }
        }
        return collect;
    }
}
