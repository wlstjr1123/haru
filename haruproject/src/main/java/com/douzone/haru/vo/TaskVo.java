package com.douzone.haru.vo;

import java.util.List;

public class TaskVo {

	private long taskListNo;
	private long taskNo;
	private String taskName;
	private String taskStart;
	private String taskEnd;
	private String taskLabel; //color
	private String taskState; //'do','done','del'
	private String taskContents;
	private long taskOrder;
	private String taskRegdate;
	private String taskWriter;
	private String projectTitle; // 업무에 속한 프로젝트명
	
	private List<TagListVo> tagListVo;
	

	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}	
	
	public List<TagListVo> getTagListVo() {
		return tagListVo;
	}
	public void setTagListVo(List<TagListVo> tagListVo) {
		this.tagListVo = tagListVo;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public long getTaskListNo() {
		return taskListNo;
	}
	public void setTaskListNo(long taskListNo) {
		this.taskListNo = taskListNo;
	}
	public long getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(long taskNo) {
		this.taskNo = taskNo;
	}
	public String getTaskStart() {
		return taskStart;
	}
	public void setTaskStart(String taskStart) {
		this.taskStart = taskStart;
	}
	public String getTaskEnd() {
		return taskEnd;
	}
	public void setTaskEnd(String taskEnd) {
		this.taskEnd = taskEnd;
	}
	public String getTaskLabel() {
		return taskLabel;
	}
	public void setTaskLabel(String taskLabel) {
		this.taskLabel = taskLabel;
	}
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	public String getTaskContents() {
		return taskContents;
	}
	public void setTaskContents(String taskContents) {
		this.taskContents = taskContents;
	}
	public long getTaskOrder() {
		return taskOrder;
	}
	public void setTaskOrder(long taskOrder) {
		this.taskOrder = taskOrder;
	}
	public String getTaskRegdate() {
		return taskRegdate;
	}
	public void setTaskRegdate(String taskRegdate) {
		this.taskRegdate = taskRegdate;
	}
	public String getTaskWriter() {
		return taskWriter;
	}
	public void setTaskWriter(String taskWriter) {
		this.taskWriter = taskWriter;
	}
	
	@Override
	public String toString() {
		return "TaskVo [taskListNo=" + taskListNo + ", taskNo=" + taskNo + ", taskName=" + taskName + ", taskStart="
				+ taskStart + ", taskEnd=" + taskEnd + ", taskLabel=" + taskLabel + ", taskState=" + taskState
				+ ", taskContents=" + taskContents + ", taskOrder=" + taskOrder + ", taskRegdate=" + taskRegdate
				+ ", taskWriter=" + taskWriter + ", projectTitle=" + projectTitle + ", tagListVo=" + tagListVo + "]";
	}	
	
	
	
}
