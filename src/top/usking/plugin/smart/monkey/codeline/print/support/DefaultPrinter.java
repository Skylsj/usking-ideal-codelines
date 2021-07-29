package top.usking.plugin.smart.monkey.codeline.print.support;

import top.usking.plugin.smart.monkey.codeline.print.Printer;
import top.usking.plugin.smart.monkey.data.MonkeyDataCenter;
import top.usking.plugin.smart.monkey.data.MonkeyModel;

import java.util.List;

/**
 * 默认打印实现类.
 */
public class DefaultPrinter implements Printer {

    /**
     * 把打印的数据放到数据中心.
     *
     * @param models 需要打印的数据 {@link MonkeyModel}.
     */
    @Override
    public void print(List<MonkeyModel> models) {
        MonkeyDataCenter.addAll(models);
    }
}
