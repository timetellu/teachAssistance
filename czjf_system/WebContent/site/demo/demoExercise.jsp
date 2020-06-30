<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="在编辑器上输入 Java 代码，可在线编译运行...">
	<title>配套源码-在线练习</title>

	<script src="${pageContext.request.contextPath}/js/flexibility.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.734ff02b7d136f770049d396e015061f.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rc-calendar.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lrtk.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" />
	<link rel='dns-prefetch' href='//s.w.org' />
	<link rel='stylesheet' id='wpProQuiz_front_style-css' href='https://c.runoob.com/wp-content/plugins/Wp-Pro-Quiz/css/wpProQuiz_front.min.css?ver=0.37' type='text/css' media='all' />
	<link rel="canonical" href="https://c.runoob.com/compile/10" />

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
	<script src="${pageContext.request.contextPath}/js/modernizr.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	
	<script src="//cdn.staticfile.org/codemirror/5.48.2/codemirror.min.js"></script>
	<script src="https://cdn.staticfile.org/codemirror/5.48.2/addon/mode/simple.min.js"></script>
	<link rel="stylesheet" href="//cdn.staticfile.org/codemirror/5.48.2/codemirror.min.css">

	<script src="//cdn.staticfile.org/codemirror/5.48.2/mode/clike/clike.js"></script>
	<link href="https://cdn.staticfile.org/normalize/7.0.0/normalize.min.css" rel="stylesheet">

	<!-- Bootstrap Core CSS -->
	<link href="//cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom CSS -->
	<link href="/wp-content/themes/toolrunoob/startbootstrap/css/modern-business.css" rel="stylesheet">

	<!-- Custom Fonts -->
	<link href="/wp-content/themes/toolrunoob/assets/font-awesome-4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

	<link rel="stylesheet" href="/wp-content/themes/toolrunoob/style.css?version=1.03">

	<script src="https://cdn.staticfile.org/jquery/2.2.4/jquery.min.js"></script>

	<script src="https://cdn.staticfile.org/clipboard.js/2.0.4/clipboard.min.js"></script>
	
	<style type="text/css">
		.textarea {
            width: 120%;
            min-height: 120px;
            max-height: 510px;
            margin-top: 30px;
            margin-left: left;
            margin-right: auto;
            padding: 3px;
            outline: 0;
            border: 1px solid #a0b3d6;
            font-size: 12px;
            line-height: 24px;
            word-wrap: break-word;
            overflow-x: hidden;
            overflow-y: auto;
            border-color: rgba(82, 168, 236, 0.8);
            box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1), 0 0 8px rgba(82, 168, 236, 0.6);
        }
	</style>
</head>

<body>
	<div class="container">
	  <div class="row">
	  
	     <div class="col-md-2 left-content">
	     	<div style="text-align:left;">
				<h4>${cour.cName}</h4>
				<hr>
			 </div>
	     	<div class="textarea" contenteditable="false">
				 <!-- 第一个菜单start -->
				 <div id="firstpane" class="menu_list">
					<c:forEach begin="1" end="${chapaterNums}" var="i" step="1" varStatus="s">
						<p class="menu_head current">第${i}章</p>
						<c:forEach items="${list}" var="demo"  varStatus="status">
							<c:set var="str" value="${i}${'.'}"></c:set> 
							<c:if test="${fn:startsWith(demo.demoName, str)}">
							    <div style="display:block" class="menu_body" >
					      			<a id="demoFill" onclick="demoFill('${demo.demoId}')">
					      				${demo.demoName}-${demo.attachmentOldName}
					      			</a>
					      		</div>
							</c:if>
					    </c:forEach>
				  	</c:forEach>
				  	<br/>
		        </div>
	     	</div>
	     	
	        <!-- 第一个菜单end -->

	    </div>
	     
		 <div class="col-md-9">
		 	<!--start-->
			<div class="index-form">
	
				<!--logo start-->
				<div class="index-banner-col page-header">
					<h1>课程配套DEMO <small>在线编程练习</small></h1>
				</div>
				<!--logo end-->
	
				<div class="index-tabs-col" >
					<!--上传文件start-->
					<span class="tabs-header tab-file ">
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
					<span class="tab-url"><a href="${pageContext.request.contextPath}/ProgramServlet?method=uploadProgram" style="text-decoration: none;">上传文件</a></span>
					</span>
					<!--上传文件end-->
	
					<!--输入代码start-->
					<span class="tabs-header tab-active">
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
					<span class="tab-url">输入代码</span>
					</span>
					<!--输入代码end-->
	
					<span class="tabs-header">  
						<div id="compiler">  <!-- 此处没写div class="panel-heading"  -->
							<form class="form-inline" role="form">
								<button type="button" class="btn btn-success" id="submitBTN" ><i class="fa fa-send-o"></i> 点击运行</button>
								<button type="button" class="btn btn-default" id="clearCode"><i class="fa fa-eraser" aria-hidden="true"></i> 清空</button>
							</form>
						</div>
					</span>
	
	
					<!--下划线右134-->
					<span class="tab-animation--active" style="left: 134px;">
					::before
					::after
				</span>
				</div>
			
			</div>
			
			<!--start-->
				<div class="container">
					<style>
						.CodeMirror {
							height: 374px;
						}
					</style>
					<br>
					<div class="row">
	
						<div class="col-md-12">
							<div class="panel panel-default">
								<!--<div id="compiler" class="panel-heading">
								<form class="form-inline" role="form">
									<button type="button" class="btn btn-success" id="submitBTN" disabled="disabled"><i class="fa fa-send-o"></i> 点击运行</button>
									<button type="button" class="btn btn-default" id="clearCode"><i class="fa fa-eraser" aria-hidden="true"></i> 清空</button>
								</form>
							</div>-->
								<div class="panel-body">
									<form role="form" id="compiler-form">
										<div class="form-group">
											<div class="row">
												<div class="col-md-7 ">
													<textarea rows="15" id="inputCode" class="form-control" >
请输入代码或者点击左侧链接填充代码</textarea>
											<br/>
											<textarea name="code" rows="3" id="systemIn" class="form-control">
请在此输入需要从键盘输入的数据【换行或空格作为分隔符】
											</textarea>
												</div>
												<div class="col-md-5">
													<textarea placeholder="运行结果……" class="form-control" id="compiler-textarea-result" rows="23">Hello World!</textarea>
												</div>
											</div>
										</div>
	
									</form>
								</div>
							</div>
						</div>
					</div>
	
	
					<div class="col-md-12">
						<style>
							.responsive_ad1 {
								display: none;
							}
							
							@media(min-width: 800px) {
								.responsive_ad1 {
									display: block;
									margin: 0 auto;
								}
							}
						</style>
						<script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
						<!-- 移动版 自动调整 -->
						<ins class="adsbygoogle responsive_ad1" style="min-width:400px;max-width:1200px;width:100%;height:90px;" data-ad-client="ca-pub-5751451760833794" data-ad-slot="1691338467" data-full-width-responsive="true"></ins>
						<script>
							(adsbygoogle = window.adsbygoogle || []).push({});
						</script>
					</div>
					<br>
					<hr>
	
				</div>
	
				<div style="display:none;">
	
	
				</div>
				<!--end-->
		   </div>
	    </div>
	</div>
	<script>

		var editor = CodeMirror.fromTextArea(document.getElementById("inputCode"), {
			lineNumbers: true,
			matchBrackets: true,
			mode: "text/x-java",
			indentUnit: 4,
			indentWithTabs: true,
		});
	
		btn = $("#submitBTN");
		editor.on("change", function(editor, change) {
			btn.prop('disabled', false);
		});
		
		
		btn.click(function() {
			btn.prop('disabled', true);
			loadingdata = '程序正在运行中……';
			$("#compiler-textarea-result").val(loadingdata);
	
			code = editor.getValue();
			stdin = '';
			if($('#stdin').length > 0) {
				stdin = $("#stdin").val();
			}
			token = '4381fe197827ec87cbac9552f14ec62a';
			var systemIn = $("#systemIn").val();
			$.post("${pageContext.request.contextPath}/ProgramServlet", 
					{"method":"runInputCode","code":code,"systemIn":systemIn,"stdin":stdin,"token":token}, 
					function(data) {
						$("#compiler-textarea-result").val(data);
			 		}
			);
			setTimeout(function() {
				btn.prop('disabled', false);
			}, 10 * 1000);
		});
		$("#clearCode").click(function() {
			var r = confirm("确认清空？");
			if(r == true) {
				editor.setValue("");
				editor.clearHistory();
				$("#compiler-textarea-result").val("");
				btn.prop('disabled', true);
			}
		});
		
		var _hmt = _hmt || [];
		(function() {
			// 统计
			var hm = document.createElement("script");
			hm.src = "https://hm.baidu.com/hm.js?68c6d4f0f6c20c5974b17198fa6fd40a";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
		$(function() {
			//代码高亮
			$('pre').each(function() {
				if(!$(this).hasClass("prettyprint")) {
					$(this).addClass("prettyprint");
				}
			});
		})
		
		function demoFill(demoId){
			//向服务端发起ajax请求
			$.post("${pageContext.request.contextPath}/DemoServlet",{"method":"fillDemoToInput","id":demoId},function(data){
				editor.setValue(data);
			});
		}
		
	<%--
		 $(document).ready(function(){
			  	$("#firstpane .menu_body:eq(0)").show();
			  	$("#firstpane p.menu_head").click(function(){
			  		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
			  		$(this).siblings().removeClass("current");
			  	});
			  	$("#secondpane .menu_body:eq(0)").show();
			  	$("#secondpane p.menu_head").mouseover(function(){
			  		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
			  		$(this).siblings().removeClass("current");
			  	});
	    });
	--%>
	</script>
	<script defer="" src="${pageContext.request.contextPath}/js/svgxuse.min.js"></script>
	<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/manifest.bundle.74351d291b887c671f60.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/app.bundle.74351d291b887c671f60.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/wxshare.bundle.74351d291b887c671f60.js"></script>

	</body>

</html>