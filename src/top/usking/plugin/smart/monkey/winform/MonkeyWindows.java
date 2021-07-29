package top.usking.plugin.smart.monkey.winform;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import top.usking.plugin.smart.monkey.codeline.IPrinterService;
import top.usking.plugin.smart.monkey.codeline.PrinterServiceFactory;
import top.usking.plugin.smart.monkey.data.MonkeyDataCenter;
import top.usking.plugin.smart.monkey.utils.PropertiesUtils;

import javax.swing.*;
import java.util.Objects;


public class MonkeyWindows {

    private final IPrinterService printerService;
    private JButton btnAdd;
    private JTable contentTable;
    private JPanel jContent;
    private JButton btnClean;

    public MonkeyWindows(Project project, ToolWindow toolWindow) {
        printerService = PrinterServiceFactory.getPrinterService();
        init(project);
    }

    public JPanel getJContent() {
        return jContent;
    }

    public void init(Project project) {
        contentTable.setModel(MonkeyDataCenter.getTableModel());
        contentTable.setEnabled(true);
        btnAdd.addActionListener(e -> {
            MonkeyDataCenter.reset();
            MonkeyDataCenter.getTableModel().setNumRows(0);
            String allPath = PropertiesUtils.getPropertyValue(project.getBasePath() + "/code-lines.properties", "paths");
            if (Objects.isNull(allPath) || "".equals(allPath)) {
                allPath = project.getBasePath();
            }
            String[] paths = PropertiesUtils.getPaths(allPath);
            printerService.print(paths);
            MonkeyDataCenter.getDataList().forEach(item -> {
                String name = item.getName();
                int total = item.getTotal();
                int code = item.getCode();
                int comment = item.getComment();
                int blank = item.getBlank();
                Object[] COLUMN_NAME = {name, total, code, comment, blank, (code * 100 / total),
                        (comment * 100 / total), (blank * 100 / total)};
                MonkeyDataCenter.getTableModel().addRow(COLUMN_NAME);
            });
        });
        btnClean.addActionListener(e -> {
            MonkeyDataCenter.reset();
            MonkeyDataCenter.getTableModel().setNumRows(0);
        });
    }
}
