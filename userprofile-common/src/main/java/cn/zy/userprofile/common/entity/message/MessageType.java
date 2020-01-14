package cn.zy.userprofile.common.entity.message;

/**
 * Created by hufenggang on 2020/1/7.
 *
 * 消息类别：
 *
 */
public enum MessageType {
    INFO(1, "info"),
    WARN(2, "warn"),
    ERROR(3, "error");

    private int typeId;
    private String typeName;

    MessageType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "MessageType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
