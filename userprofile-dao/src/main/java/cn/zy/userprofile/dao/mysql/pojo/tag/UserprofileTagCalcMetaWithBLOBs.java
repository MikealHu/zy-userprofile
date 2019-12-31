package cn.zy.userprofile.dao.mysql.pojo.tag;

import java.io.Serializable;

/**
 * userprofile_tag_calc_meta
 * @author 
 */
public class UserprofileTagCalcMetaWithBLOBs extends UserprofileTagCalcMeta implements Serializable {
    /**
     * 标签编号，统一标签主题有多个编号用逗号分隔
     */
    private String tagIds;

    /**
     * 标签计算sql语句
     */
    private String calcSql;

    private static final long serialVersionUID = 1L;

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getCalcSql() {
        return calcSql;
    }

    public void setCalcSql(String calcSql) {
        this.calcSql = calcSql;
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
        UserprofileTagCalcMetaWithBLOBs other = (UserprofileTagCalcMetaWithBLOBs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTagTheme() == null ? other.getTagTheme() == null : this.getTagTheme().equals(other.getTagTheme()))
            && (this.getTagType() == null ? other.getTagType() == null : this.getTagType().equals(other.getTagType()))
            && (this.getIsValid() == null ? other.getIsValid() == null : this.getIsValid().equals(other.getIsValid()))
            && (this.getTagIds() == null ? other.getTagIds() == null : this.getTagIds().equals(other.getTagIds()))
            && (this.getCalcSql() == null ? other.getCalcSql() == null : this.getCalcSql().equals(other.getCalcSql()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTagTheme() == null) ? 0 : getTagTheme().hashCode());
        result = prime * result + ((getTagType() == null) ? 0 : getTagType().hashCode());
        result = prime * result + ((getIsValid() == null) ? 0 : getIsValid().hashCode());
        result = prime * result + ((getTagIds() == null) ? 0 : getTagIds().hashCode());
        result = prime * result + ((getCalcSql() == null) ? 0 : getCalcSql().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagIds=").append(tagIds);
        sb.append(", calcSql=").append(calcSql);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}