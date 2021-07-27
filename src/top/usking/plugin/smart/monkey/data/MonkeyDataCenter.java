package top.usking.plugin.smart.monkey.data;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

public class MonkeyDataCenter {
    private static String[] COLUMN_NAME = {"文件名", "总行数", "代码行数", "注释行数","空行行数", "代码(%)", "注释(%)","空行(%)"};
    public static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null, COLUMN_NAME);
    public static List<MonkeyModel> NOTE_LIST = new LinkedList<>();
    public static void reset() {
        NOTE_LIST.clear();
    }
}
