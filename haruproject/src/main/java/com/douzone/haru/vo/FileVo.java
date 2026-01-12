package com.douzone.haru.vo;

//파일 생성
//종윤
public class FileVo {
	private Long userNo; // 회원 번호
	private String userName; //회원 이름
	
	private String projectNo; // 프로젝트 번호
	private String projectTitle; // 프로젝트 이름
	
	private Long tasklistNo; // 태스트리스트 번호
	private String tasklistName; // 테스트리스트 이름
	
	private Long taskNo; //업무번호
	private String taskContents; //업무 내용
	private String taskState; // 업무상태
	
	
	private Long fileNo; // 파일번호
	private String filePath; // 파일경로
	private String originName; // 원본이름
	private String changeName; // 변경이름
	private String fileRegdate; // 파일 등록일
	private String fileState; // 파일 상태
	private String fileMaker; // 파일 생성 유저
	
	
	public FileVo() {
	}
	
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public Long getTasklistNo() {
		return tasklistNo;
	}
	public void setTasklistNo(Long tasklistNo) {
		this.tasklistNo = tasklistNo;
	}
	public String getTasklistName() {
		return tasklistName;
	}
	public void setTasklistName(String tasklistName) {
		this.tasklistName = tasklistName;
	}
	public Long getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(Long taskNo) {
		this.taskNo = taskNo;
	}
	public String getTaskContents() {
		return taskContents;
	}
	public void setTaskContents(String taskContents) {
		this.taskContents = taskContents;
	}
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getFileRegdate() {
		return fileRegdate;
	}
	public void setFileRegdate(String fileRegdate) {
		this.fileRegdate = fileRegdate;
	}
	public String getFileState() {
		return fileState;
	}
	public void setFileState(String fileState) {
		this.fileState = fileState;
	}
	public String getFileMaker() {
		return fileMaker;
	}
	public void setFileMaker(String fileMaker) {
		this.fileMaker = fileMaker;
	}

	@Override
	public String toString() {
		return String.format("FileVo [userNo=%s, userName=%s, projectNo=%s, projectTitle=%s, tasklistNo=%s, tasklistName=%s, taskNo=%s, taskContents=%s, taskState=%s, fileNo=%s, filePath=%s, originName=%s, changeName=%s, fileRegdate=%s, fileState=%s, fileMaker=%s]", userNo, userName, projectNo,
				projectTitle, tasklistNo, tasklistName, taskNo, taskContents, taskState, fileNo, filePath, originName, changeName, fileRegdate, fileState, fileMaker);
	}
	
	

	
	
	
	
	
	
	
}
