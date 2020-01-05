package cn.zy.userprofile.common.entity.task;

import java.util.List;

/**
 * Author: hufenggang
 * Email: hufenggang2019@gmail.com
 * Date: 2020/1/4
 */
public class ZyTaskDefault {
    private Long taskId;
    private String taskName;
    private String taskType;
    private List<String> dependenies;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public List<String> getDependenies() {
        return dependenies;
    }

    public void setDependenies(List<String> dependenies) {
        this.dependenies = dependenies;
    }
}
