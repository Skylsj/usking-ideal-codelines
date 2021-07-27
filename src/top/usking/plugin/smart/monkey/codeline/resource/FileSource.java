package top.usking.plugin.smart.monkey.codeline.resource;

import top.usking.plugin.smart.monkey.utils.PropertiesUtils;

import java.util.List;

public class FileSource implements FileResource {
    @Override
    public List<String> getResources(String... paths) {
        return PropertiesUtils.getAllFiles(paths);
    }
}
