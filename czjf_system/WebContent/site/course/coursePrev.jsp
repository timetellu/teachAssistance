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
      
      <!-- left__start -->
      <div id="left">
	       <jsp:include flush="true" page="/site/left.jsp"></jsp:include>
      </div>
      <!-- left__end -->
      
      
      <div id="right">
      	  <!-- 111 -->
      	  <h2>最近查看课程记录</h2>
	      <div id="welcome">
	        <div>
	           <div class="c1-body">

					<c:forEach items="${list}"  var="d">
	                    <div class="c1-bline" style="padding:7px 0px;">
			                    <div class="f-left">
			                          <img src="/czjf_system/img/head-mark4.gif"
			                               align="middle" 
			                               class="img-vm" border="0"/> 
			                          <a href="${pageContext.request.contextPath}/DocumentServlet?method=findDocumentById&id=${d.docId}"  class="pn-loperator" style="color: red">
			                             ${d.docName}
			                          </a>
			                     </div>
			                     <div class="f-right">${d.uploadTime}</div>
			                     <div class="clear"></div>
		                </div>
		           </c:forEach>
                    
  				   <div class="pg-3"></div>		  
     		   </div>
	        </div>
	        <p class="more"><a href="${pageContext.request.contextPath}/DocumentServlet?method=findDocumentsWithPage&num=1">更多</a></p>
	        
	      </div>
	      <!-- 111 -->
      </div>
      
      <div class="clear"> </div>
      
      
      <div id="footer">
	      <div id="copyright">
	        Copyright &copy;TIMETELL版权所有&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/czjf_system/login.jsp">系统后台登陆</a>
	      </div>
	      <div id="footerline"></div>
      </div>
  </div>
</body>
</html>
