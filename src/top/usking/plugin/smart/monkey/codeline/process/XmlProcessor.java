package top.usking.plugin.smart.monkey.codeline.process;

public class XmlProcessor extends AbstractProcessor {
    private static final String FILE_SUFFIX = "xml";
    private static final String MULTI_COMMENT_START = "<!--";
    private static final String MULTI_COMMENT_END = "-->";

    @Override
    boolean isCommentLine(String line) {
        return line.startsWith(MULTI_COMMENT_START)
                || line.endsWith(MULTI_COMMENT_END);
    }

    @Override
    public boolean canProcess(String suffix) {
        return FILE_SUFFIX.equals(suffix);
    }

    @Override
    boolean isCommentMultiLineStart(String line) {
        return line.startsWith(MULTI_COMMENT_START);
    }

    @Override
    boolean isCommentMultiLineEnd(String line) {
        return line.endsWith(MULTI_COMMENT_END);
    }
}
