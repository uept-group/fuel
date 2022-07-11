package tech.uept.fuel.admin.basic.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class MessagePage {

    private Page<MessageView> page;

    private String taskId;

    public MessagePage(Page<MessageView> page, String taskId) {
        this.page = page;
        this.taskId = taskId;
    }

    public Page<MessageView> getPage() {
        return page;
    }

    public void setPage(Page<MessageView> page) {
        this.page = page;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "MessagePage{" + "page=" + page + ", taskId='" + taskId + '\'' + '}';
    }
}
