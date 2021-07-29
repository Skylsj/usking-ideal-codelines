package top.usking.plugin.smart.monkey.codeline.process;

import top.usking.plugin.smart.monkey.data.MonkeyModel;

import java.util.List;

/**
 * 处理接口
 */
public interface Processor {

    /**
     * 判断是否能被处理
     *
     * @param suffix 文件后缀名.
     * @return true of false.
     */
    boolean canProcess(String suffix);

    /**
     * 计算文件内容,构建{@link MonkeyModel}.
     *
     * @param fileName 文件名.
     * @param list     文件里的数据.
     * @return 处理后的数据.
     */
    MonkeyModel process(String fileName, List<String> list);
}
