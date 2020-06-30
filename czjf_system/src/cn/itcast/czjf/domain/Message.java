package cn.itcast.czjf.domain;

public class Message {

	private int messageId; //留言编号
	private String content; //留言内容
	private String leaveWordTime;//留言时间
	private int stuId;//当前登录在系统中的学生的编号
	private String replay;  //老师的回复内容
	private String replayTime; //老师的回复时间
	public String stuRealname;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLeaveWordTime() {
		return leaveWordTime;
	}
	public void setLeaveWordTime(String leaveWordTime) {
		this.leaveWordTime = leaveWordTime;
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getReplay() {
		return replay;
	}
	public void setReplay(String replay) {
		this.replay = replay;
	}
	public String getReplayTime() {
		return replayTime;
	}
	public void setReplayTime(String replayTime) {
		this.replayTime = replayTime;
	}
	public String getStuRealname() {
		return stuRealname;
	}
	public void setStuRealname(String stuRealname) {
		this.stuRealname = stuRealname;
	}
	
	
}
