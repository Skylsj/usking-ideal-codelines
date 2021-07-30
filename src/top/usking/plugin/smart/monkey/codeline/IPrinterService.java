package top.usking.plugin.smart.monkey.codeline;

/**
 * 打印服务.
 */
public interface IPrinterService {

    /**
     * 打印方法.
     *
     * @param paths 要打印的文件路径.
     */
    void print(String... paths);
}
