package cn.zy.userprofile.common.entity.flow;

/**
 * Created by hufenggang on 2020/1/5.
 */
public interface Flow {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Boolean getStatue();

    void setStatue(Boolean statue);
}
