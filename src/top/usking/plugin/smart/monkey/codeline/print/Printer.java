package top.usking.plugin.smart.monkey.codeline.print;

import top.usking.plugin.smart.monkey.data.MonkeyModel;

import java.util.List;

/**
 * 打印接口.
 */
public interface Printer {

    /**
     * 打印.
     *
     * @param models 需要打印的数据 {@link MonkeyModel}.
     */
    void print(List<MonkeyModel> models);
}
