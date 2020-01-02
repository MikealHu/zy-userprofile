package cn.zy.userprofile.tag.entity;


/**
 * created by hufenggang on 2020/1/2
 */
public class TagCalcTask {
    private String tagTheme;
    private String tagType;
    private String tagIds;
    private String calcSql;

    public TagCalcTask() {
    }

    public TagCalcTask(String tagTheme, String tagType, String tagIds, String calcSql) {
        this.tagTheme = tagTheme;
        this.tagType = tagType;
        this.tagIds = tagIds;
        this.calcSql = calcSql;
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
}
