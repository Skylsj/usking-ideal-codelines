package top.usking.plugin.smart.monkey.codeline;

import top.usking.plugin.smart.monkey.codeline.print.Printer;
import top.usking.plugin.smart.monkey.codeline.process.Context;
import top.usking.plugin.smart.monkey.codeline.process.ProcessorFactory;
import top.usking.plugin.smart.monkey.codeline.resource.FileResource;
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
    private final FileResource fileResource;
    private final ProcessorFactory processorFactory;

    /**
     * Printer
     * ProcessorFactory
     * fileResource
     *
     * @param printer
     * @param processorFactory
     * @param fileResource
     */
    public PrinterService(Printer printer, ProcessorFactory processorFactory, FileResource fileResource) {
        this.printer = printer;
        this.fileResource = fileResource;
        this.processorFactory = processorFactory;
    }

    @Override
    public void print(String... paths) {
        List<MonkeyModel> list1 = new ArrayList<>();
        int sourceCodeLines = 0;
        int commentLines = 0;
        int blankLines = 0;
        List<String> allFiles = fileResource.getResources(paths);
        try {
            for (String filePath : allFiles) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (sourceCodeLines > 0) {
            printer.print(Arrays.asList(new MonkeyModel("TOTAL:", sourceCodeLines, commentLines, blankLines)));
        }

        printer.print(list1);
    }
}
