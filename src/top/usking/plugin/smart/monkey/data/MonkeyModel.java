package top.usking.plugin.smart.monkey.data;

/**
 * 统计内容对象类.
 */
public class MonkeyModel {
    //文件名称
    private String name;
    //总行数
    private int total;
    //代码行数
    private int code;
    //注释行数
    private int comment;
    //空格行数
    private int blank;

    public MonkeyModel(String name, int code, int comment, int blank) {
        this.name = name;
        this.total = code + comment + blank;
        this.code = code;
        this.comment = comment;
        this.blank = blank;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public int getCode() {
        return code;
    }

    public int getComment() {
        return comment;
    }

    public int getBlank() {
        return blank;
    }

    @Override
    public String toString() {
        return "MonkeyModel{" +
                "name='" + name + '\'' +
                ", total=" + total +
                ", code=" + code +
                ", comment=" + comment +
                ", blank=" + blank +
                '}';
    }
}
