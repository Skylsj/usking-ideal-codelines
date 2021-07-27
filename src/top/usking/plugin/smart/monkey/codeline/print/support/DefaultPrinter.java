package top.usking.plugin.smart.monkey.codeline.print.support;

import top.usking.plugin.smart.monkey.codeline.print.Printer;
import top.usking.plugin.smart.monkey.data.MonkeyDataCenter;
import top.usking.plugin.smart.monkey.data.MonkeyModel;

import java.util.List;

public class DefaultPrinter implements Printer {

    @Override
    public void print(List<MonkeyModel> models) {
        MonkeyDataCenter.NOTE_LIST.addAll(models);
    }
}
