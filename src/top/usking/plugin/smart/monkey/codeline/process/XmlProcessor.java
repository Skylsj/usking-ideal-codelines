package top.usking.plugin.smart.monkey.codeline.process;

public class XmlProcessor extends AbstractProcessor{
    private static final String FILE_SUFFIX="xml";
    private static final String MULTI_COMMENT_START = "<!--";
    private static final String MULTI_COMMENT_END = "-->";
    @Override
    boolean isCommentLine(String line) {
        String result = line.trim();
        return result.startsWith(MULTI_COMMENT_START)
                || result.startsWith(MULTI_COMMENT_END);
    }

    @Override
    public boolean canProcess(String suffix) {
        return FILE_SUFFIX.equals(suffix);
    }
}
