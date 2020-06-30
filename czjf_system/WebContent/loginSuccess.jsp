<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>在线程序设计实验教学辅助系统</title>
  </head>
  
  <body>
       <script type="text/javascript">
           function tiao()
           {
               <s:if test="#session.userType==0">
                   window.location.href="/czjf_system/admin/index.jsp";
               </s:if>
               <s:if test="#session.userType==1">
                   window.location.href="/czjf_system/atea/index.jsp";
               </s:if>
           }
           
           setTimeout(tiao,1300)
       </script>
       <br> <br> <br> <br> <br> <br> <br> <br> <br>
       <center><img src="/czjf_system/img/loading.gif">页面跳转中</center>
  </body>
</html>
