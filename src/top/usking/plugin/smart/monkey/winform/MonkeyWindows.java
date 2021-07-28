package top.usking.plugin.smart.monkey.winform;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import top.usking.plugin.smart.monkey.codeline.IPrinterService;
import top.usking.plugin.smart.monkey.codeline.PrinterServiceFactory;
import top.usking.plugin.smart.monkey.utils.PropertiesUtils;

import javax.swing.*;

import java.util.Objects;

import static top.usking.plugin.smart.monkey.data.MonkeyDataCenter.NOTE_LIST;
import static top.usking.plugin.smart.monkey.data.MonkeyDataCenter.TABLE_MODEL;


public class MonkeyWindows {

    private final IPrinterService printerService;
    private JButton btnAdd;
    private JTable contentTable;
    private JPanel jContent;
    private JButton btnClean;

    public MonkeyWindows(Project project, ToolWindow toolWindow) {
        printerService = PrinterServiceFactory.getPrinterService();
        System.out.println("==================================");
        System.out.println(project.getBasePath());
        System.out.println(project.getProjectFilePath());
        System.out.println(project.getPicoContainer());
        init(project);
    }

    public JPanel getJContent() {
        return jContent;
    }

    public void init(Project project) {
        contentTable.setModel(TABLE_MODEL);
        contentTable.setEnabled(true);
        btnAdd.addActionListener(e -> {
            NOTE_LIST.clear();
            TABLE_MODEL.setNumRows(0);
            String allPath = PropertiesUtils.getPropertyValue(project.getBasePath() + "/code-lines.properties", "paths");
            if (Objects.isNull(allPath) || "".equals(allPath)) {
                allPath = project.getBasePath();
            }
            String[] paths = PropertiesUtils.getPaths(allPath);
            printerService.print(paths);
            NOTE_LIST.forEach(item -> {
                String name = item.getName();
                int total = item.getTotal();
                int code = item.getCode();
                int comment = item.getComment();
                int blank = item.getBlank();
                Object[] COLUMN_NAME = {name, total, code, comment, blank, (code * 100 / total),
                        (comment * 100 / total), (blank * 100 / total)};
                TABLE_MODEL.addRow(COLUMN_NAME);
            });
        });
        btnClean.addActionListener(e -> {
            NOTE_LIST.clear();
            TABLE_MODEL.setNumRows(0);
        });
    }
}
