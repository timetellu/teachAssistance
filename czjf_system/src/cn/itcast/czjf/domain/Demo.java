package cn.itcast.czjf.domain;

public class Demo {
	private int demoId;            //demo编号
	private String demoName;       //demo名称
	private String demoAttachment; //demo在目录中的真实名称
	private String attachmentOldName; //demo附件原始名称
	private String uploadTime;    //demo上传时间
	private String del;		      //demo是否删除
	private int status;           //demo是否公开
	private int cId;
	private String cNum;          //上传文档所属课程编号
	private String cName;         //上传文档所属课程名称
	public int getDemoId() {
		return demoId;
	}
	public void setDemoId(int demoId) {
		this.demoId = demoId;
	}
	public String getDemoName() {
		return demoName;
	}
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}
	public String getDemoAttachment() {
		return demoAttachment;
	}
	public void setDemoAttachment(String demoAttachment) {
		this.demoAttachment = demoAttachment;
	}
	public String getAttachmentOldName() {
		return attachmentOldName;
	}
	public void setAttachmentOldName(String attachmentOldName) {
		this.attachmentOldName = attachmentOldName;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	@Override
	public String toString() {
		return "Demo [demoId=" + demoId + ", demoName=" + demoName + ", demoAttachment=" + demoAttachment
				+ ", attachmentOldName=" + attachmentOldName + ", uploadTime=" + uploadTime + ", del=" + del
				+ ", status=" + status + ", cId=" + cId + ", cNum=" + cNum + ", cName=" + cName + "]";
	}
	
	
	
	
	
}
