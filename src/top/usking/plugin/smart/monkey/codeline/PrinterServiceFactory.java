package top.usking.plugin.smart.monkey.codeline;

import top.usking.plugin.smart.monkey.codeline.print.Printer;
import top.usking.plugin.smart.monkey.codeline.print.support.DefaultPrinter;
import top.usking.plugin.smart.monkey.codeline.process.JavaProcessor;
import top.usking.plugin.smart.monkey.codeline.process.Processor;
import top.usking.plugin.smart.monkey.codeline.process.ProcessorFactory;
import top.usking.plugin.smart.monkey.codeline.process.XmlProcessor;
import top.usking.plugin.smart.monkey.codeline.resource.FileResource;
import top.usking.plugin.smart.monkey.codeline.resource.FileSource;

import java.util.Arrays;

public class PrinterServiceFactory {

    private static Processor javaProcessor = new JavaProcessor();
    private static Processor xmlProcessor = new XmlProcessor();
    private static Printer printer = new DefaultPrinter();
    private static FileResource fileResource = new FileSource();
    private static ProcessorFactory processorFactory = new ProcessorFactory(Arrays.asList(javaProcessor, xmlProcessor));
    private static IPrinterService printerService = new PrinterService(printer, processorFactory, fileResource);

    public static IPrinterService getPrinterService() {
        return printerService;
    }
}
