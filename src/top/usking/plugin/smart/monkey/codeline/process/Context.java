package top.usking.plugin.smart.monkey.codeline.process;

import top.usking.plugin.smart.monkey.data.MonkeyModel;

import java.util.List;

public class Context {

    private Processor processor;

    public Context(Processor processor) {
        this.processor = processor;
    }

    public MonkeyModel process(String fileName, List<String> list) {

        return processor.process(fileName, list);
    }
}
