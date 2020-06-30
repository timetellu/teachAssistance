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
      	  <h2>课程资料详情</h2>
	      <div id="welcome">
	        <div>
	           <div class="c1-body">
                   <table width="100%" align="center" border="0" cellpadding="8" cellspacing="8">
					    <tr>
					        <td width="20%" align="left" style="font-size: 13px;">
					              课程名称：									    
					        </td>
					        <td width="80%" align="left" style="font-size: 13px;">
					            ${demo.cName} 
					        </td>
					    </tr>
					    <tr>
					        <td width="20%" align="left" style="font-size: 13px;">
					              资料名称：									    
					        </td>
					        <td width="80%" align="left" style="font-size: 13px;">
					            ${demo.demoName} 
					        </td>
					    </tr>
					    <tr>
					        <td width="20%" align="left" style="font-size: 13px;">
					              资料附件：									    
					        </td>
					        <td width="80%" align="left" style="font-size: 13px;">
					            <a href="${pageContext.request.contextPath}/DemoServlet?method=downloadDemo&id=${demo.demoId}" style="font-size: 13px;color: red">${demo.attachmentOldName}</a>
					        </td>
					    </tr>
					    <tr>
					        <td width="20%" align="left" style="font-size: 13px;">
					              发布时间：									    
					        </td>
					        <td width="80%" align="left" style="font-size: 13px;">
					            ${demo.uploadTime}
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
