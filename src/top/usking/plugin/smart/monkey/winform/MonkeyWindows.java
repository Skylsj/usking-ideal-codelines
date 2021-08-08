package top.usking.plugin.smart.monkey.winform;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.file.PsiDirectoryFactory;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
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
            PsiFile[] filesByName = FilenameIndex.getFilesByName(project, "code-lines.properties",
                    GlobalSearchScope.projectScope(project));
//            Module[] modules = ModuleManager.getInstance(project).getModules();
//            @NotNull String[] allFilenames = FilenameIndex.getAllFilenames(project);

            String allPath;
            if (filesByName.length < 1) {
                allPath = null;
            } else {
                String text = filesByName[0].getText();
                allPath = PropertiesUtils.getPropertyValue(text, "paths");
            }
            if (Objects.isNull(allPath)) {
                String title = "创建文件";
                String msg = "您好,代码统计时,需要在当前项目根目前下生成一个code-lines.properties\r\n" +
                        "请去里面做一下配置.默认是统计项目内的所有文件\r\n" +
                        "目前只支持java,properties,xml,yml这四种文件";
                Messages.showMessageDialog(project, msg, title, Messages.getInformationIcon());
                createConfig(project);
            }
            String[] paths = PropertiesUtils.getPaths(project.getBasePath(), allPath);
            printerService.print(paths);
            MonkeyDataCenter.getDataList().forEach(item -> {
                String name = item.getName();
                int total = item.getTotal();
                int code = item.getCode();
                int comment = item.getComment();
                int blank = item.getBlank();
                String codePercent = String.format("%.2f%%", code * 100d / total);
                String commentPercent = String.format("%.2f%%", comment * 100d / total);
                String blankPercent = String.format("%.2f%%", blank * 100d / total);
                Object[] COLUMN_NAME = {name, total, code, comment, blank,
                        "0.00%".equals(codePercent) ? "0" : codePercent,
                        "0.00%".equals(commentPercent) ? "0" : commentPercent,
                        "0.00%".equals(blankPercent) ? "0" : blankPercent
                };
                MonkeyDataCenter.getTableModel().addRow(COLUMN_NAME);
            });

        });
        btnClean.addActionListener(e -> {
            MonkeyDataCenter.reset();
            MonkeyDataCenter.getTableModel().setNumRows(0);

        });
    }

    private void createConfig(Project project) {

        WriteCommandAction.runWriteCommandAction(project, () -> {
            StringBuilder text = new StringBuilder();
            text.append("# 1.paths=/src/main,/src/test");
            text.append("\n");
            text.append("# 2.paths=/src/main/java/com/Hello.java,/code-lines.properties");
            text.append("\n");
            text.append("# 3.paths=/src/main,/code-lines.properties");
            text.append("\n");
            text.append("# 4.default is project base path");
            text.append("\n");
            text.append("paths=");
            PsiFile propertiesFile = PsiFileFactory.getInstance(project)
                    .createFileFromText("code-lines.properties", PlainTextFileType.INSTANCE, text.toString());
            PsiDirectory directory = PsiDirectoryFactory.getInstance(project)
                    .createDirectory(ProjectUtil.guessProjectDir(project));
            directory.add(propertiesFile);
        });
    }
}
