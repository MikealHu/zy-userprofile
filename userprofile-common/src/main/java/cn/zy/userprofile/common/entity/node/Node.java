package cn.zy.userprofile.common.entity.node;

import java.util.List;

/**
 * Created by hufenggang on 2020/1/5.
 */
public interface Node {
    String getId();

    void setId(String id);

    String getType();

    void setType(String type);

    String getName();

    void setName(String name);

    void addDependency(String nodeName);

    void setDependencies(List<String> dependency);

    void removeDependency(String nodeName);

    List<String> getDependencies();
}
