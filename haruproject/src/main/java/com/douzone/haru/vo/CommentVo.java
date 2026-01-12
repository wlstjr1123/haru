package com.douzone.haru.vo;

//코멘트
//종윤
public class CommentVo {
	private Long taskNo; // 업무 번호
	private Long userNo; // 회원 번호
	private String userName; //회원 이름
	private String userPhoto; //회원 프로필
	private String userEmail; //회원 이메일

	private Long commentNo; // 코멘트 번호
	private String commentRegdate; // 코멘트 등록일
	private String commentContents; // 코멘트 내용
	private String commentState; // 코멘트 상태

	public CommentVo() {
	}

	public Long getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(Long taskNo) {
		this.taskNo = taskNo;
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

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(Long commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentRegdate() {
		return commentRegdate;
	}

	public void setCommentRegdate(String commentRegdate) {
		this.commentRegdate = commentRegdate;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}

	public String getCommentState() {
		return commentState;
	}

	public void setCommentState(String commentState) {
		this.commentState = commentState;
	}

	@Override
	public String toString() {
		return String.format("CommentVo [taskNo=%s, userNo=%s, userName=%s, userPhoto=%s, userEmail=%s, commentNo=%s, commentRegdate=%s, commentContents=%s, commentState=%s]", taskNo, userNo, userName, userPhoto, userEmail, commentNo, commentRegdate, commentContents, commentState);
	}

}
