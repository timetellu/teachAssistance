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
      	  <h2>师生交流</h2>
	      <div id="welcome">
	        <div>
	           <div class="c1-body">
	              <!-- 遍历留言 start -->
			      <c:forEach items="${page.list}" var="message" varStatus="sta">
                       <div class="c1-bline" style="padding:7px 0px;">
                        <div class="f-left">
                             <img src="/czjf_system/img/head-mark4.gif" align="middle" class="img-vm" border="0"/> 
                             <a href="javascript:void(0)" onclick="messageDetail(${message.messageId})">${message.content}</a>
                        </div>
                        <div class="f-right">${message.leaveWordTime}</div>
                        <div class="clear"></div>
                       </div>
                   </c:forEach>
                  <!-- 遍历留言 end -->
               
                  <center>
                  	<a href="${pageContext.request.contextPath}/MessageServlet?method=addMessageUI" style="color: black">
                  		发布留言
                  	</a>
                  </center>
				  <div class="pg-3"></div>		  
		       </div>
	        </div>
	        <%@ include file="/jsp/pageFile.jsp" %>
	      </div>
	      <!-- 111 -->
      </div>
      
      
      
     <%@ include file="/site/footer.jsp" %>
  </div>
</body>
<script type="text/javascript">

        function messageDetail(id)
        {
             var strUrl = "${pageContext.request.contextPath}/MessageServlet?method=findMessageById&id="+id;
             var ret = window.open(strUrl,"_self","dialogWidth:800px; dialogHeight:500px; dialogLeft: status:no; directories:yes;scrollbars:yes;Resizable=no;");
        }
    </script>
</html>
