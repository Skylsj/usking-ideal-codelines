package top.usking.plugin.smart.monkey.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public abstract class PropertiesUtils {

    public static String[] getPaths(String pathContent) {
        return pathContent.split(",");
    }

    public static String getPropertyValue(String path, String key) {
        try {
            FileInputStream in = new FileInputStream(path);
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

                    try {
                        for (File f : listFiles) {
                            if (f.isFile()) {
                                collect.add(f.getCanonicalPath());
                            }
                        }
                        return collect;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (file.isFile()) {
                    collect.add(path);
                }

            }
        }
        return collect;
    }
}