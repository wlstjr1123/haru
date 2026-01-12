package com.douzone.haru.vo;

import java.util.List;

//프로젝트
public class ProjectVo {
	private Long projectNo; // 프로젝트 no
	private String projectTitle; // 프로젝트 제목
	private String projectDesc; // 프로젝트 내용
	private String projectStart; // 프로젝트 시작일
	private String projectEnd; // 프로젝트 마감일
	private String projectState; // 프로젝트 상태 (T:진행중,F:완료됨)
	private String projectRegDate; // 프로젝스 생성일
	private Long userNo; // 프로젝트 작성자 no
	private List<UserVo> members; // 프로젝트 멤버
	private Long taskCount; // 업무 갯수
	private Long completedTask; // 완료된 업무 수
	private Long sessionUserNo; // 로그인된 유저 no
	private String ownership; // 방장 권한(O:방장,N:멤버)
	private String ownerName; //방장 이름
	
	@Override
	public String toString() {
		return "ProjectVo [projectNo=" + projectNo + ", projectTitle=" + projectTitle + ", projectDesc=" + projectDesc
				+ ", projectStart=" + projectStart + ", projectEnd=" + projectEnd + ", projectState=" + projectState
				+ ", projectRegDate=" + projectRegDate + ", userNo=" + userNo + ", members=" + members + ", taskCount="
				+ taskCount + ", completedTask=" + completedTask + ", sessionUserNo=" + sessionUserNo + ", ownership="
				+ ownership + ", ownerName=" + ownerName + "]";
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Long getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(Long projectNo) {
		this.projectNo = projectNo;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public String getProjectStart() {
		return projectStart;
	}
	public void setProjectStart(String projectStart) {
		this.projectStart = projectStart;
	}
	public String getProjectEnd() {
		return projectEnd;
	}
	public void setProjectEnd(String projectEnd) {
		this.projectEnd = projectEnd;
	}
	public String getProjectState() {
		return projectState;
	}
	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}
	public String getProjectRegDate() {
		return projectRegDate;
	}
	public void setProjectRegDate(String projectRegDate) {
		this.projectRegDate = projectRegDate;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public List<UserVo> getMembers() {
		return members;
	}
	public void setMembers(List<UserVo> members) {
		this.members = members;
	}
	public Long getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(Long taskCount) {
		this.taskCount = taskCount;
	}
	public Long getCompletedTask() {
		return completedTask;
	}
	public void setCompletedTask(Long completedTask) {
		this.completedTask = completedTask;
	}
	public Long getSessionUserNo() {
		return sessionUserNo;
	}
	public void setSessionUserNo(Long sessionUserNo) {
		this.sessionUserNo = sessionUserNo;
	}
	public String getOwnership() {
		return ownership;
	}
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}
}
