package top.usking.plugin.smart.monkey.data;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据中心.
 */
public abstract class MonkeyDataCenter {
    private static List<MonkeyModel> DATA_LIST = new LinkedList<>();
    private static String[] COLUMN_NAME = {"文件名", "总行数", "代码行数", "注释行数", "空行行数", "代码(%)", "注释(%)", "空行(%)"};
    private static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null, COLUMN_NAME);

    /**
     * 向数据中心添加数据.
     *
     * @param models 被添加的数据.
     */
    public static void addAll(List<MonkeyModel> models) {
        DATA_LIST.addAll(models);
    }

    /**
     * 清空数据.
     */
    public static void reset() {
        DATA_LIST.clear();
    }

    /**
     * 取数据.
     *
     * @return 返回数据.
     */
    public static List<MonkeyModel> getDataList() {
        return DATA_LIST;
    }

    /**
     * 返回tableMode对象.
     *
     * @return 返回tableMode对象.
     */
    public static DefaultTableModel getTableModel() {
        return TABLE_MODEL;
    }
}
