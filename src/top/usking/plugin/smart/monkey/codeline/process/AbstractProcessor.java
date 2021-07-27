package top.usking.plugin.smart.monkey.codeline.process;

import top.usking.plugin.smart.monkey.data.MonkeyModel;
import top.usking.plugin.smart.monkey.utils.StringHelper;

import java.util.List;

public abstract class AbstractProcessor implements Processor {
    @Override
    public MonkeyModel process(String fileName,List<String> list) {

        int space = 0;
        int comment = 0;
        int code = 0;
        boolean isComment = false;
        for (String line : list) {
            //1.空格
            line = line.trim();
            if (line.length() == 0) {
                space++;
            } else if (isComment || isCommentLine(line)) {
                if (isCommentMultiLineStart(line)) {
                    isComment = true;
                }
                if (isCommentMultiLineEnd(line)) {
                    isComment = false;
                }
                comment++;
            } else {
                code++;
            }
        }

        return new MonkeyModel(StringHelper.getFilename(fileName), code, comment, space);
    }

    abstract boolean isCommentLine(String line);

    boolean isCommentMultiLineStart(String line) {
        return false;
    }

    boolean isCommentMultiLineEnd(String line) {
        return false;
    }

}
