package top.usking.plugin.smart.monkey.codeline;

import top.usking.plugin.smart.monkey.codeline.print.support.DefaultPrinter;
import top.usking.plugin.smart.monkey.codeline.process.JavaProcessor;
import top.usking.plugin.smart.monkey.codeline.process.JsonProcessor;
import top.usking.plugin.smart.monkey.codeline.process.ProcessorFactory;
import top.usking.plugin.smart.monkey.codeline.process.PropertiesProcessor;
import top.usking.plugin.smart.monkey.codeline.process.XmlProcessor;
import top.usking.plugin.smart.monkey.codeline.process.YmlProcessor;
import top.usking.plugin.smart.monkey.codeline.resource.FileSource;

/**
 * 工厂,负责组装打印服务.
 */
public class PrinterServiceFactory {

    /* 定义工厂 */
    private static ProcessorFactory PROCESSOR_FACTORY = new ProcessorFactory(
            new JavaProcessor(),
            new XmlProcessor(),
            new PropertiesProcessor(),
            new JsonProcessor(),
            new YmlProcessor()
    );
    /* 定义实现了IPrinterService接口的一个服务 */
    private static IPrinterService PRINTER_SERVICE = new PrinterService(new DefaultPrinter(), PROCESSOR_FACTORY, new FileSource());

    /**
     * 获取打印服务.
     *
     * @return 返回打印服务.
     */
    public static IPrinterService getPrinterService() {
        return PRINTER_SERVICE;
    }
}
