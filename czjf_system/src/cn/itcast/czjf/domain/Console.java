package cn.itcast.czjf.domain;

public class Console {
	private String attachmentOldName;
	private String jdkVersion;
	private String serverVersion;
	private String mysqlVersion;
	private String submitTime;
	private String runResult;
	
	public String getAttachmentOldName() {
		return attachmentOldName;
	}
	public void setAttachmentOldName(String attachmentOldName) {
		this.attachmentOldName = attachmentOldName;
	}
	public String getJdkVersion() {
		return jdkVersion;
	}
	public void setJdkVersion(String jdkVersion) {
		this.jdkVersion = jdkVersion;
	}
	public String getServerVersion() {
		return serverVersion;
	}
	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}
	public String getMysqlVersion() {
		return mysqlVersion;
	}
	public void setMysqlVersion(String mysqlVersion) {
		this.mysqlVersion = mysqlVersion;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getRunResult() {
		return runResult;
	}
	public void setRunResult(String runResult) {
		this.runResult = runResult;
	}
	@Override
	public String toString() {
		return "Console [attachmentOldName=" + attachmentOldName + ", jdkVersion=" + jdkVersion + ", serverVersion="
				+ serverVersion + ", mysqlVersion=" + mysqlVersion + ", submitTime=" + submitTime + ", runResult="
				+ runResult + "]";
	}
	
	
	
}
