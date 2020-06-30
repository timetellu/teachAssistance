package cn.itcast.czjf.domain;

public class Document {
	private int docId;            //资料编号
	private String docName;       //资料名称
	private String docAttachment; //资料在目录中的真实名称
	private String attachmentOldName; //资料附件原始名称
	private String uploadTime;    //资料上传时间
	private String del;		      //资料是否删除
	private int status;           //资料是否公开
	private String cNum;          //上传文档所属课程编号
	private String cName;         //上传文档所属课程名称
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocAttachment() {
		return docAttachment;
	}
	public void setDocAttachment(String docAttachment) {
		this.docAttachment = docAttachment;
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
		return "Document [docId=" + docId + ", docName=" + docName + ", docAttachment=" + docAttachment
				+ ", attachmentOldName=" + attachmentOldName + ", uploadTime=" + uploadTime + ", del=" + del
				+ ", status=" + status + ", cNum=" + cNum + ", cName=" + cName + "]";
	}
	
	
	
	
	
}
