package top.usking.plugin.smart.monkey.codeline.process;

public class YmlProcessor extends AbstractProcessor {

    private static final String FILE_SUFFIX = "yml";
    private static final String SINGLE_COMMENT = "#";

    @Override
    boolean isCommentLine(String line) {
        return line.startsWith(SINGLE_COMMENT);
    }

    @Override
    public boolean canProcess(String suffix) {
        return FILE_SUFFIX.equals(suffix);
    }

}
