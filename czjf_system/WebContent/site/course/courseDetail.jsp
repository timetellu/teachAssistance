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
	
	<link rel="stylesheet" href="/czjf_system/css/qiantai.css" type="text/css" charset="utf-8" />
	
	<style type="text/css">
		.c1-bline{border-bottom:#999 1px dashed;border-top:1px;}
		.f-right{float:right}
		.f-left{float:left}
		.clear{clear:both}
    </style>
	
  </head>
  
  <body>
  <div id="wrapper">
  
      <div id="header"></div>
      
      <div id="left">
	      <jsp:include flush="true" page="/site/left.jsp"></jsp:include>
      </div>
      
      <div id="right">
      	  <!-- 111 -->
      	  <h2>课程名：${cour.cName}</h2>
	      <div id="welcome">
	        <div>
	           <div class="c1-body">
                   <table width="100%" align="center" border="0" cellpadding="8" cellspacing="8">
					    <tr>
					        <td width="20%" align="left" style="font-size: 13px;">
					             课程资料：									    
					        </td>
					        <td width="80%" align="left" style="font-size: 13px;">
					            <a href="${pageContext.request.contextPath}/DocumentServlet?method=findDocumentsWithPageByCourse&num=1&cId=${cour.cId}" style="font-size: 13px;">点击查看课程学习课件</a>
					        </td>
					    </tr>
					    
					    <tr>
					        <td width="20%" align="left" style="font-size: 13px;">
					             课程视频：									    
					        </td>
					        <td width="80%" align="left" style="font-size: 13px;">
					            <a href="${pageContext.request.contextPath}/VedioServlet?method=findVedioWithPageByCourse&num=1&cId=${cour.cId}" style="font-size: 13px;">点击查看课程学习视频</a>
					        </td>
					    </tr>
					    
					    <tr>
					        <td width="20%" align="left" style="font-size: 13px;">
					             课程源码：									    
					        </td>
					        <td width="80%" align="left" style="font-size: 13px;">
					            <!-- 
					            <a href="${pageContext.request.contextPath}/DemoServlet?method=findDemosWithPageByCourse&num=1&cId=${cour.cId}" style="font-size: 13px;">点击查看课程配套源码</a>
					             -->
					            <a href="${pageContext.request.contextPath}/DemoServlet?method=findDemoExerciseByCourse&num=1&cId=${cour.cId}" style="font-size: 13px;">点击查看课程配套源码</a>
					        </td>
					    </tr>
					   
				   </table> 
		       </div>
	        </div>
	      </div>
      </div>
	<%@ include file="/site/footer.jsp" %>      
      
  </div>
</body>
</html>
