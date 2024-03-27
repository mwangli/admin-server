package online.mwang.ems.commom.enums;

/**
 * 数据状态说明
 *
 * @author mwangli
 * @date 2020/12/15 15:34
 **/
public enum StatusEnum {

    /**
     * 可用
     */
    AVAILABLE(1, "可用"),

    /**
     * 删除
     */
    DELETED(0, "删除");

    /**
     * 索引
     */
    private final Integer index;

    /**
     * 说明
     */
    private final String description;

    StatusEnum(Integer index, String description) {
        this.index = index;
        this.description = description;
    }

    public Integer getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }
}
