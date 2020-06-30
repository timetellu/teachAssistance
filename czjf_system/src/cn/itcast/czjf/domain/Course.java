package cn.itcast.czjf.domain;

public class Course {

	private int cId;            //课程id
	private String cNum;        //课程编号
	private String cName;       //课程名称
	private String teaNum;
	private String teaRealName;
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
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
	@Override
	public String toString() {
		return "Course [cId=" + cId + ", cNum=" + cNum + ", cName=" + cName + ", teaNum=" + teaNum + ", teaRealName="
				+ teaRealName + "]";
	}
	
	

	
}
