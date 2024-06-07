package com.poscodx.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String contents;
	private Integer hit;
	private String regDate;
	private Integer gNo;
	private Integer oNo;
	private Integer depth;
	private Long userNo;
	private String userName;
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String user_name) {
		this.userName = user_name;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Integer getHit() {
		return hit;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Integer getGno() {
		return gNo;
	}
	public void setGno(Integer gNo) {
		this.gNo = gNo;
	}
	public Integer getOno() {
		return oNo;
	}
	public void setOno(Integer oNo) {
		this.oNo = oNo;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public Long getuserNo() {
		return userNo;
	}
	public void setuserNo(Long userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", hit=" + hit + ", regDate="
				+ regDate + ", gNo=" + gNo + ", oNo=" + oNo + ", depth=" + depth + ", userNo=" + userNo + "]";
	}
	
}
