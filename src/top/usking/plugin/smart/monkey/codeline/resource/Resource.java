package top.usking.plugin.smart.monkey.codeline.resource;

import java.util.List;

/**
 * 资源接口
 */
public interface Resource {

    /**
     * 获取资源数据.
     *
     * @param paths
     * @return
     */
    List<String> getResources(String... paths);
}
