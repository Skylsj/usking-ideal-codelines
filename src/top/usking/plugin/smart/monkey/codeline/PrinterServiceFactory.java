package top.usking.plugin.smart.monkey.codeline;

import top.usking.plugin.smart.monkey.codeline.print.support.DefaultPrinter;
import top.usking.plugin.smart.monkey.codeline.process.*;
import top.usking.plugin.smart.monkey.codeline.resource.FileSource;

public class PrinterServiceFactory {

    private static ProcessorFactory processorFactory = new ProcessorFactory(
            new JavaProcessor(),
            new XmlProcessor(),
            new PropertiesProcessor(),
            new JsonProcessor(),
            new YmlProcessor()
    );
    private static IPrinterService printerService = new PrinterService(new DefaultPrinter(), processorFactory, new FileSource());

    public static IPrinterService getPrinterService() {
        return printerService;
    }
}
