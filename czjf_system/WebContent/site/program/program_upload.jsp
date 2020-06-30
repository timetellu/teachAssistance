<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  	 <meta name="description" content="在线编程">
    <meta name="keyword" content="在线编程">
    <title>在线编程-上传文件</title>
    
	<script src="${pageContext.request.contextPath}/js/flexibility.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.734ff02b7d136f770049d396e015061f.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rc-calendar.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"/>
</head>

<body>
	
	<!--start-->
	<div class="index-form">
		
		<!--logo start-->
		<div class="index-banner-col page-header">
			 <h1>实验教学辅助系统 <small>在线编程</small></h1>
		</div>
		<!--logo end-->
		
		
		<div class="index-tabs-col">
			<!--上传文件start-->
			<span class="tabs-header tab-file tab-active">
				<span class="hover-icon " title="">
					<svg width="18px" height="18px" viewBox="0 0 18 18" version="1.1">
						<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
							<g transform="translate(1.000000, 1.000000)" fill="#666666">
								<rect opacity="0.200000003" x="3" y="5" width="13" height="7"></rect>
								<path d="M2,3 L2,10 L14,10 L14,3 L12,3 L12,1 L16,1 L16,12 L0,12 L0,1 L4,1 L4,3 L2,3 Z M3,14 L13,14 L13,16 L3,16 L3,14 Z" fill-rule="nonzero"></path>
								<polygon points="8 0 5 4 11 4"></polygon>
								<rect x="7" y="4" width="2" height="4"></rect>
							</g>
						</g>
					</svg>
				</span>
				<span>上传文件</span>
			</span>
			<!--上传文件end-->
			
			<!--输入代码start-->
			<span class="tabs-header">
				<span class="hover-icon " title="">
					<svg width="20px" height="18px" viewBox="0 0 20 18" version="1.1">
						<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd" transform="translate(-639.000000, -280.000000)">
							<g transform="translate(509.000000, 280.000000)" fill="#666666"><g transform="translate(130.000000, 0.000000)">
								<g transform="translate(0.000000, 3.000000)">
									<path d="M9,2 L6,2 C3.790861,2 2,3.790861 2,6 C2,8.209139 3.790861,10 6,10 L9,10 L9,12 L6,12 C2.6862915,12 4.05812251e-16,9.3137085 0,6 C-4.05812251e-16,2.6862915 2.6862915,6.08718376e-16 6,0 L9,0 L9,2 Z" fill-rule="nonzero"></path>
									<path d="M20,2 L17,2 C14.790861,2 13,3.790861 13,6 C13,8.209139 14.790861,10 17,10 L20,10 L20,12 L17,12 C13.6862915,12 11,9.3137085 11,6 C11,2.6862915 13.6862915,6.08718376e-16 17,0 L20,0 L20,2 Z" fill-rule="nonzero" transform="translate(15.500000, 6.000000) scale(-1, 1) translate(-15.500000, -6.000000) "></path>
									<rect opacity="0.200000003" x="5" y="4" width="14" height="8" rx="4"></rect><rect x="5" y="5" width="10" height="2"></rect>
								</g>
							</g>
							</g>
						</g>
					</svg>
				</span>
				<span  class="tab-url"><a href="${pageContext.request.contextPath}/ProgramServlet?method=inputProgram" style="text-decoration: none;">输入代码</a></span>
			</span>
			<!--输入代码end-->
			
			<!--下划线左0-->
			<span class="tab-animation--active" style="left: 0px;">
				::before
				::after
			</span>
		</div>
		
		<div class="index-upload-file" ">
			<div class="index-upload-file__icon" id="upload_code_file">
				<img src="${pageContext.request.contextPath}/img/b3c21c89.file-draging.gif"  onclick="fileSelect()">
			</div>
			<p class="placeholder ellipsis" >
				 点击文件夹开始上传，文件大小不超过 20MB
			</p>
			<form id="fm" action="${pageContext.request.contextPath}/ProgramServlet?method=runUploadCode" method="post" enctype="multipart/form-data">
				<input type="file" name="attachmentOldName" id="fileToUpload" style="display:none;"/>
			</form>
		</div>
		
		
	</div>
	
	<!--end-->
	
	<script defer="" src="${pageContext.request.contextPath}/js/svgxuse.min.js"></script>
	<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/manifest.bundle.74351d291b887c671f60.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/app.bundle.74351d291b887c671f60.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/wxshare.bundle.74351d291b887c671f60.js"></script>
	<script type="text/javascript">

		 function fileSelect() {
		    $("#fileToUpload").click(); 
		 }
 
	    $("#fileToUpload").trigger("input"); 
		$("#fileToUpload").bind("input propertychange",
				function () 
				{
			        $("#fm").submit();
			    }
		);
	</script>
	
</body>
</html>