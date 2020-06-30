<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width">
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0"> 
		<meta name="description" content="程序在线运行">
		<meta name="keyword" content="程序在线运行">
		<title>程序在线运行-运行结果</title>

		<script src="${pageContext.request.contextPath}/js/flexibility.js"></script>
		<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.734ff02b7d136f770049d396e015061f.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rc-calendar.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" />
		<link href="${pageContext.request.contextPath}/css/report.367deb1103373f02a4a83044fec4a1f6.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rc-slider.css" />
	</head>

	<body>

		<div class="global-drop-box report-global-upload" aria-disabled="false" style="position: relative;">
			<div class="">
				<div class="page-content" id="sample_content">
					<div class="container"></div>
					<div>
						<div class="main-content">
							<div data-block-name="分析概要" class="sandbox-info" cellpadding="0" cellspacing="0">
								<div class="sandbox-info__container">
									<div class="sandbox-info__basic">
										<div class="sandbox-info__result">
											<svg class="threat-type-icon">
												<use xlink:href="#safe--selector-circle-fill-7ed321"></use>
											</svg><span class="safe">该文件运行结果如下</span>
										</div>
										<br />
										<div class="sandbox-info__item">
											<div class="sandbox-info__item-label">文件名称：</div>
											<div class="sandbox-info__item-value ellipsis">${console.attachmentOldName}</div>
										</div>
										<div class="sandbox-info__item">
											<div class="sandbox-info__item-label">JDK版本：</div>
											<div class="sandbox-info__item-value ellipsis">${console.jdkVersion}</div>
										</div>
										<div class="sandbox-info__item">
											<div class="sandbox-info__item-label">服务器版本：</div>
											<div class="sandbox-info__item-value ellipsis">${console.serverVersion}</div>
										</div>
										<div class="sandbox-info__item">
											<div class="sandbox-info__item-label">MySQL版本：</div>
											<div class="sandbox-info__item-value ellipsis">${console.mysqlVersion}</div>
										</div>
										<div class="sandbox-info__item">
											<div class="sandbox-info__item-label">提交时间：</div>
											<div class="sandbox-info__item-value ellipsis">${console.submitTime}</div>
										</div>
										
									</div>

									<div class="sandbox-info__file-type">
										<img src="${pageContext.request.contextPath}/img/share_logo.png" style="width: 90px; height: 100px;" />
										<!--评分功能：先留着，以后可能会用到
											<div class="sandbox-info__score">0分
											<div class="sandbox-info__score-describe"><span class="hover-icon " title=""><svg width="14px" height="14px" viewBox="0 0 14 14" version="1.1"><g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd"><g transform="translate(-1274.000000, -251.000000)"><g transform="translate(380.000000, 90.000000)"><g transform="translate(857.000000, 116.000000)"><g transform="translate(37.000000, 45.000000)"><circle stroke="#2B75C8" cx="7" cy="7" r="6.5"></circle><path d="M6.88,3 C7.66,3 8.296,3.204 8.776,3.636 C9.256,4.056 9.496,4.632 9.496,5.364 C9.496,5.964 9.34,6.456 9.052,6.84 C8.944,6.96 8.596,7.284 8.02,7.788 C7.804,7.968 7.648,8.172 7.54,8.388 C7.42,8.628 7.36,8.88 7.36,9.168 L7.36,9.336 L5.98,9.336 L5.98,9.168 C5.98,8.712 6.052,8.316 6.22,7.992 C6.376,7.668 6.844,7.164 7.624,6.468 L7.768,6.3 C7.984,6.036 8.092,5.748 8.092,5.448 C8.092,5.052 7.972,4.74 7.756,4.512 C7.528,4.284 7.204,4.176 6.796,4.176 C6.268,4.176 5.896,4.332 5.668,4.668 C5.464,4.944 5.368,5.34 5.368,5.844 L4,5.844 C4,4.956 4.252,4.26 4.78,3.756 C5.296,3.252 5.992,3 6.88,3 Z M6.664,9.924 C6.928,9.924 7.156,10.008 7.336,10.176 C7.504,10.344 7.6,10.56 7.6,10.824 C7.6,11.088 7.504,11.316 7.324,11.484 C7.144,11.652 6.928,11.736 6.664,11.736 C6.4,11.736 6.184,11.64 6.004,11.472 C5.824,11.304 5.74,11.088 5.74,10.824 C5.74,10.56 5.824,10.344 6.004,10.176 C6.184,10.008 6.4,9.924 6.664,9.924 Z" id="？" fill="#2B75C8"></path></g></g></g></g></g></svg></span>
												<dl><dt>威胁评分：</dt>
													<dd>60-100分，判为恶意</dd>
													<dd>30-59分，判为可疑</dd>
													<dd>0-29分，判为正常</dd>
												</dl>
											</div>
										</div>-->
										<div class="sandbox-info__score">
											<span class="sandbox-info__btn" id="btn_reupload">重新上传</span>
										</div>
									</div>
								</div>

							</div>
							<div class="sandbox-sample-content">
								<div class="col-md-5">
									<textarea placeholder="运行结果……" class="form-control" id="compiler-textarea-result" rows="22"  style="margin-bottom: 12px; margin-left: -18px; width: 765px;">
${console.runResult}
									</textarea>
								</div>
								
							</div>

						</div>
						<input type="file" autocomplete="off" style="position: absolute; top: 0px; right: 0px; bottom: 0px; left: 0px; opacity: 1e-05; pointer-events: none;">
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
				$("#btn_reupload").click(function(){
					location.href = "${pageContext.request.contextPath}/ProgramServlet?method=uploadProgram";
				});
				
			});
		</script>
	</body>

</html>