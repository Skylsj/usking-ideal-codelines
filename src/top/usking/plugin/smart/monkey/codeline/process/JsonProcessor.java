package top.usking.plugin.smart.monkey.codeline.process;

public class JsonProcessor extends AbstractProcessor {

    private static final String FILE_SUFFIX = "json";

    @Override
    boolean isCommentLine(String line) {
        return false;
    }

    @Override
    public boolean canProcess(String suffix) {
        return FILE_SUFFIX.equals(suffix);
    }

}
