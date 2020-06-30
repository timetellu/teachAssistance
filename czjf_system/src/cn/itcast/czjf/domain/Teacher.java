package cn.itcast.czjf.domain;

public class Teacher {

	private int teaId;
	private String teaNum;
	private String teaRealName;
	private String teaSex;
	private String teaAge;
	private String loginName;
	private String loginPwd;
	private String del;
	
	public int getTeaId() {
		return teaId;
	}
	public void setTeaId(int teaId) {
		this.teaId = teaId;
	}
	public String getTeaNum() {
		return teaNum;
	}
	public void setTeaNum(String teaNum) {
		this.teaNum = teaNum;
	}
	public String getTeaRealName() {
		return teaRealName;
	}
	public void setTeaRealName(String teaRealName) {
		this.teaRealName = teaRealName;
	}
	public String getTeaSex() {
		return teaSex;
	}
	public void setTeaSex(String teaSex) {
		this.teaSex = teaSex;
	}
	public String getTeaAge() {
		return teaAge;
	}
	public void setTeaAge(String teaAge) {
		this.teaAge = teaAge;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	@Override
	public String toString() {
		return "Teacher [teaId=" + teaId + ", teaNum=" + teaNum + ", teaRealName=" + teaRealName + ", teaSex=" + teaSex
				+ ", teaAge=" + teaAge + ", loginName=" + loginName + ", loginPwd=" + loginPwd + ", del=" + del + "]";
	}
	
	
	
}
