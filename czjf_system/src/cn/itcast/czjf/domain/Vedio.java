package cn.itcast.czjf.domain;

public class Vedio {
	private int vedioId;    //视频编号
	private String vedioName; //视频名称
	private String vedioPro; //视频简介
	private String vedioAttachment; //视频附件名称
	private String attachmentOldName; //视频附件原始名称
	private String uploadTime;//视频上传时间
	private String del;//视频是否删除
	private int status;           //视频是否公开
	private String cNum;          //上传文档所属课程编号
	private String cName;         //上传文档所属课程名称
	public int getVedioId() {
		return vedioId;
	}
	public void setVedioId(int vedioId) {
		this.vedioId = vedioId;
	}
	public String getVedioName() {
		return vedioName;
	}
	public void setVedioName(String vedioName) {
		this.vedioName = vedioName;
	}
	public String getVedioPro() {
		return vedioPro;
	}
	public void setVedioPro(String vedioPro) {
		this.vedioPro = vedioPro;
	}
	public String getVedioAttachment() {
		return vedioAttachment;
	}
	public void setVedioAttachment(String vedioAttachment) {
		this.vedioAttachment = vedioAttachment;
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
	@Override
	public String toString() {
		return "Vedio [vedioId=" + vedioId + ", vedioName=" + vedioName + ", vedioPro=" + vedioPro
				+ ", vedioAttachment=" + vedioAttachment + ", attachmentOldName=" + attachmentOldName + ", uploadTime="
				+ uploadTime + ", del=" + del + ", status=" + status + ", cNum=" + cNum + ", cName=" + cName + "]";
	}
	
	
	
	
}
