package top.usking.plugin.smart.monkey.codeline.process;


public class JavaProcessor extends AbstractProcessor {

    private static final String FILE_SUFFIX="java";
    private static final String SINGLE_COMMENT = "//";
    private static final String MULTI_COMMENT_START = "/*";
    private static final String MULTI_COMMENT_MIDDLE = "*";
    private static final String MULTI_COMMENT_END = "*/";

    @Override
    boolean isCommentLine(String line) {
        String result = line.trim();
        return result.startsWith(SINGLE_COMMENT)
                || result.startsWith(MULTI_COMMENT_START)
                || result.startsWith(MULTI_COMMENT_MIDDLE)
                || result.endsWith(MULTI_COMMENT_END);
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
