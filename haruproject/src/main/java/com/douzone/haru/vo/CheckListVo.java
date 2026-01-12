package com.douzone.haru.vo;

//종윤
public class CheckListVo {
	private long taskNo;
	private long checklistNo;
	private String checklistContents;
	private String checklistState;
	
	public long getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(long taskNo) {
		this.taskNo = taskNo;
	}
	public long getChecklistNo() {
		return checklistNo;
	}
	public void setChecklistNo(long checklistNo) {
		this.checklistNo = checklistNo;
	}
	public String getChecklistContents() {
		return checklistContents;
	}
	public void setChecklistContents(String checklistContents) {
		this.checklistContents = checklistContents;
	}
	public String getChecklistState() {
		return checklistState;
	}
	public void setChecklistState(String checklistState) {
		this.checklistState = checklistState;
	}
	@Override
	public String toString() {
		return "ChecklistVo [taskNo=" + taskNo + ", checklistNo=" + checklistNo + ", checklistContents="
				+ checklistContents + ", checklistState=" + checklistState + "]";
	}
	
	
}
