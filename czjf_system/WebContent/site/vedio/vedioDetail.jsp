<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<title>实验教学辅助系统</title>
	<link rel="stylesheet" href="/czjf_system/css/qiantai.css" type="text/css" charset="utf-8" />
	
	<style type="text/css">
		.c1-bline{border-bottom:#999 1px dashed;border-top:1px;}
		.f-right{float:right}
		.f-left{float:left}
		.clear{clear:both}
    </style>
	
	<script type="text/javascript" src="/czjf_system/js/public.js"></script>
	
  </head>
  
  <body>
  <div id="wrapper">
      <div id="header"></div>
      <div id="left">
	      <jsp:include flush="true" page="/site/left.jsp"></jsp:include>
      </div>
      <div id="right">
      	  <!-- 111 -->
      	  <h2>课程视频详情</h2>
	      <div id="welcome">
	        <div>
	           <div class="c1-body">
                   <table width="100%" border="0">
					    <tr>
					       <td align="center">${vedio.vedioName}<hr/></td>
					    </tr>
					    <tr>
					        <td align="center" style="word-break:break-all;">视频介绍：
					        		${vedio.vedioPro}
					         <hr/>
					        </td>
					    </tr>
					    <tr>
					       <td align="center">
			                    <a href="${pageContext.request.contextPath}/VedioServlet?method=downloadVedio&id=${vedio.vedioId}" style="font-size: 13px;color: red">${vedio.vedioName}</a>
					            &nbsp;&nbsp;&nbsp;
					            <a href="${pageContext.request.contextPath}/VedioServlet?method=playVedioById&id=${vedio.vedioId}"  class="pn-loperator" style="color: red">播放</a>
					             
					            <hr/>
					       </td>
					    </tr>
					    <tr>
					       <td align="center">发布时间:${vedio.uploadTime}<hr/></td>
					    </tr>
					</table>
		       </div>
	        </div>
	        <!-- <p class="more"><a href="#">more</a></p> -->
	      </div>
	      <!-- 111 -->
      </div>
      <div class="clear"> </div>
      <div id="footer">
	      <div id="copyright">
	        Copyright &copy;TIMETELL All right reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/czjf_system/login.jsp">系统后台登陆</a>
	      </div>
	      <div id="footerline"></div>
      </div>
  </div>
</body>
</html>
