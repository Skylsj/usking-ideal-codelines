package top.usking.plugin.smart.monkey.codeline.resource;

import top.usking.plugin.smart.monkey.utils.PropertiesUtils;

import java.util.List;

/**
 * 文件资源类
 */
public class FileSource implements Resource {

    /**
     * 获取文件内容
     *
     * @param paths
     * @return
     */
    @Override
    public List<String> getResources(String... paths) {
        return PropertiesUtils.getAllFiles(paths);
    }
}
