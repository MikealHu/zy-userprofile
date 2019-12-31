package cn.zy.userprofile.dao.mysql.pojo.tag;

import java.io.Serializable;

/**
 * userprofile_tag_calc_meta
 * @author 
 */
public class UserprofileTagCalcMeta implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 标签主题
     */
    private String tagTheme;

    /**
     * 标签类型, cookie/user
     */
    private String tagType;

    /**
     * 状态，1：正常，0：生效
     */
    private Byte isValid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagTheme() {
        return tagTheme;
    }

    public void setTagTheme(String tagTheme) {
        this.tagTheme = tagTheme;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public Byte getIsValid() {
        return isValid;
    }

    public void setIsValid(Byte isValid) {
        this.isValid = isValid;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserprofileTagCalcMeta other = (UserprofileTagCalcMeta) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTagTheme() == null ? other.getTagTheme() == null : this.getTagTheme().equals(other.getTagTheme()))
            && (this.getTagType() == null ? other.getTagType() == null : this.getTagType().equals(other.getTagType()))
            && (this.getIsValid() == null ? other.getIsValid() == null : this.getIsValid().equals(other.getIsValid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTagTheme() == null) ? 0 : getTagTheme().hashCode());
        result = prime * result + ((getTagType() == null) ? 0 : getTagType().hashCode());
        result = prime * result + ((getIsValid() == null) ? 0 : getIsValid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tagTheme=").append(tagTheme);
        sb.append(", tagType=").append(tagType);
        sb.append(", isValid=").append(isValid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}