package cn.zy.userprofile.common.entity.message;

/**
 * Created by hufenggang on 2020/1/7.
 */
public abstract class MessageDefault implements Message {

    private Integer id;
    private String name;
    private String desc;

    public MessageDefault(Integer id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
