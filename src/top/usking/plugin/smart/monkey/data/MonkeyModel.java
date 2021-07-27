package top.usking.plugin.smart.monkey.data;

public class MonkeyModel {
    private String name;
    private int total;
    private int code;
    private int comment;
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
