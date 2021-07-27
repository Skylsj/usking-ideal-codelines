package top.usking.plugin.smart.monkey.codeline.process;

import top.usking.plugin.smart.monkey.data.MonkeyModel;

import java.util.List;

public interface Processor {
    boolean canProcess(String suffix);

    MonkeyModel process(String fileName, List<String> list);
}
