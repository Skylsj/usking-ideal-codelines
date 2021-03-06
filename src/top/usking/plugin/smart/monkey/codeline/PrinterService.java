package top.usking.plugin.smart.monkey.codeline;

import top.usking.plugin.smart.monkey.codeline.print.Printer;
import top.usking.plugin.smart.monkey.codeline.process.Context;
import top.usking.plugin.smart.monkey.codeline.process.ProcessorFactory;
import top.usking.plugin.smart.monkey.codeline.resource.Resource;
import top.usking.plugin.smart.monkey.data.MonkeyModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class PrinterService implements IPrinterService {
    private final Printer printer;
    private final Resource resource;
    private final ProcessorFactory processorFactory;

    /**
     * Printer
     * ProcessorFactory
     * fileResource
     *
     * @param printer
     * @param processorFactory
     * @param resource
     */
    public PrinterService(Printer printer, ProcessorFactory processorFactory, Resource resource) {
        this.printer = printer;
        this.resource = resource;
        this.processorFactory = processorFactory;
    }

    @Override
    public void print(String... paths) {
        List<MonkeyModel> list1 = new ArrayList<>();
        int sourceCodeLines = 0;
        int commentLines = 0;
        int blankLines = 0;
        List<String> allFiles = resource.getResources(paths);

        for (String filePath : allFiles) {
            try {
                List<String> list = Files.readAllLines(Paths.get(filePath));
                Context processor = processorFactory.getProcessor(filePath);
                if (processor != null) {
                    MonkeyModel model = processor.process(filePath, list);
                    if (model != null) {
                        sourceCodeLines += model.getCode();
                        commentLines += model.getComment();
                        blankLines += model.getBlank();
                        list1.add(model);
                    }
                }
            } catch (IOException e) {
                System.err.println(filePath+"IOException:"+e.getMessage());
                e.printStackTrace();
            }
        }


        if (sourceCodeLines > 0) {
            printer.print(Arrays.asList(new MonkeyModel("TOTAL:", sourceCodeLines, commentLines, blankLines)));
        }

        printer.print(list1);
    }
}
