<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
  "http://www.w3.org/TR/html4/loose.dtd">
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
	
	<script type="text/javascript">
	 function userReg()
     {
            var url="/czjf_system/site/userReg/userReg.jsp";
            var ret = window.showModalDialog(url,"","dialogWidth:800px; dialogHeight:500px; dialogLeft: status:no; directories:yes;scrollbars:yes;Resizable=no;");
     }
	 function check()
	 {                                                                                         
	     if(document.ThisForm.userName.value=="")
		 {
		 	alert("请输入账号");
			return false;
		 }
		 if(document.ThisForm.userPw.value=="")
		 {
		 	alert("请输入密码");
			return false;
		 }
		 document.getElementById("indicator").style.display="block";
		 loginService.login(document.ThisForm.userName.value,document.ThisForm.userPw.value,2,callback);
	  }
	  function callback(data)
	  {
		    document.getElementById("indicator").style.display="none";
		    if(data=="no")
		    {
		        alert("账号或密码错误");
		    }
		    if(data=="yes")
		    {   
		        alert("登陆成功");
		        var url="/czjf_system/site/default.jsp";
		        window.location.href=url;
		    }
	  }
     function logout()
     {
	    loginService.logout(callback1);
     }
     
     function callback1(data)
	 {
	    alert("退出系统");
	    window.location.reload();
	 }
    </script>
  </head>
  
  <body>
  <div id="wrapper">
     
      <!-- 头部区域start -->
      <div id="header"></div>
      <!-- 头部区域end -->
      
      
      <!-- left__start -->
      <div id="left">
	       <jsp:include flush="true" page="/site/left.jsp"></jsp:include>
      </div>
      <!-- left__end -->
      
      
      <!-- right__start -->
      <div id="right">
      	  <!-- 111 -->
      	  <h2>${cour.cName}：课程资料</h2>
	      <div id="welcome">
	        <div>
	           <div class="c1-body">
	                
			        <c:forEach items="${page.list}" var="doc" varStatus="sta">
                        <div class="c1-bline" style="padding:7px 0px;">
	                        <div class="f-left">
	                             <img src="/czjf_system/img/head-mark4.gif" align="middle" class="img-vm" border="0"/> 
	                             <a href="${pageContext.request.contextPath}/DocumentServlet?method=findDocumentById&id=${doc.docId}">
	                             	${doc.docName}
	                             </a>
	                        </div>
	                        <div class="f-right">${doc.uploadTime}</div>
	                        <div class="clear"></div>
                        </div>
                    </c:forEach>
                     
					<div class="pg-3"></div>		  
			   </div>
	        </div>
	        <%@include file="/jsp/pageFile.jsp" %>
	        <!-- <p class="more"><a href="#">more</a></p> -->
	      </div>
	      <!-- 111 -->
      </div>
      <!-- right__end -->
      
      
      <div class="clear"> </div>
      
      
      
      <!-- footer___start -->
      <div id="footer">
	      <div id="copyright">
	        Copyright &copy; TIMETELL版权所有&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/czjf_system/login.jsp">系统后台登陆</a>
	      </div>
	      <div id="footerline"></div>
      </div>
      <!-- footer___end -->
  </div>
</body>
</html>
