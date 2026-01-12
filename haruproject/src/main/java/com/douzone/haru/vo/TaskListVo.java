package com.douzone.haru.vo;

import java.util.List;

public class TaskListVo {
	private long projectNo;
	private long taskListNo;
	private String taskListName;
	private long taskListOrder;
	private String taskListState;
	
	
	private String projectTitle;
	private List<TaskVo> taskVoList;
	private TaskListVo updateTaskList;
	
	
	
	public TaskListVo getUpdateTaskList() {
		return updateTaskList;
	}
	public void setUpdateTaskList(TaskListVo updateTaskList) {
		this.updateTaskList = updateTaskList;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public List<TaskVo> getTaskVoList() {
		return taskVoList;
	}
	public void setTaskVoList(List<TaskVo> taskVoList) {
		this.taskVoList = taskVoList;
	}
	public long getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(long projectNo) {
		this.projectNo = projectNo;
	}
	public long getTaskListNo() {
		return taskListNo;
	}
	public void setTaskListNo(long taskListNo) {
		this.taskListNo = taskListNo;
	}
	public String getTaskListName() {
		return taskListName;
	}
	public void setTaskListName(String taskListName) {
		this.taskListName = taskListName;
	}
	public long getTaskListOrder() {
		return taskListOrder;
	}
	public void setTaskListOrder(long taskListOrder) {
		this.taskListOrder = taskListOrder;
	}
	public String getTaskListState() {
		return taskListState;
	}
	public void setTaskListState(String taskListState) {
		this.taskListState = taskListState;
	}
	@Override
	public String toString() {
		return "TaskListVo [projectNo=" + projectNo + ", taskListNo=" + taskListNo + ", taskListName=" + taskListName
				+ ", taskListOrder=" + taskListOrder + ", taskListState=" + taskListState + ", projectTitle="
				+ projectTitle + ", taskVoList=" + taskVoList + ", updateTaskList=" + updateTaskList + "]";
	}
	
	
	
}
