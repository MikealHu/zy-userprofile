package cn.zy.userprofile.common.entity.flow;

import cn.zy.userprofile.common.entity.task.Task;

import java.util.List;

/**
 * Created by hufenggang on 2020/1/5.
 */
public class WorkFLow implements Flow {
    // 工作流编号
    private Long id;
    // 工作流名称
    private String name;
    // 工作流描述信息
    private String description;
    // 工作流执行状态，0/1代表未完成/已完成
    private Boolean statue;
    // 工作流任务数
    private int taskCount;
    // 工作流任务集合
    private List<Task> tasks;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Boolean getStatue() {
        return this.statue;
    }

    @Override
    public void setStatue(Boolean statue) {
        this.statue = statue;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }
}
