package cn.itcast.czjf.domain;

public class Student {

	private int stuId; //编号
	private String stuNum;//学号
	private String stuRealname;//真实姓名
	private String stuSex; //性别
	private String stuAge; //年龄
	private String loginPw;//登录密码
	private int status;//状态(信用分数)
	private String del;//是否删除
	private String cNum;
	private String cName;

	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public String getStuRealname() {
		return stuRealname;
	}
	public void setStuRealname(String stuRealname) {
		this.stuRealname = stuRealname;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuAge() {
		return stuAge;
	}
	public void setStuAge(String stuAge) {
		this.stuAge = stuAge;
	}
	public String getLoginPw() {
		return loginPw;
	}
	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getcNum() {
		return cNum;
	}
	public void setcNum(String cNum) {
		this.cNum = cNum;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", stuNum=" + stuNum + ", stuRealname=" + stuRealname + ", stuSex=" + stuSex
				+ ", stuAge=" + stuAge + ", loginPw=" + loginPw + ", status=" + status + ", del=" + del + ", cNum="
				+ cNum + ", cName=" + cName + "]";
	}
	
	
	
	
	
	
}
