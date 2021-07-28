package top.usking.plugin.smart.monkey.codeline.process;

import javax.annotation.Nullable;

public class ProcessorFactory {

    private final Processor[] processors;

    public ProcessorFactory(Processor... processors){
        this.processors = processors;
    }

    public Context getProcessor(String fileName) {
        Context result = null;
        for (Processor processor : processors) {
            if (processor.canProcess(getFilenameExtension(fileName))) {
                result =new Context(processor);
                break;
            }
        }
        return result;
    }

    public static String getFilenameExtension(@Nullable String path) {
        if (path == null) {
            return null;
        } else {
            int extIndex = path.lastIndexOf(46);
            if (extIndex == -1) {
                return null;
            } else {
                int folderIndex = path.lastIndexOf("/");
                return folderIndex > extIndex ? null : path.substring(extIndex + 1);
            }
        }
    }
}
