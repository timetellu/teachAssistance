<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<title>在线程序设计实验教学辅助系统</title>
	<link rel="stylesheet" href="/czjf_system/css/qiantai.css" type="text/css" charset="utf-8" />
  </head>
  
  <body>
  <div id="wrapper">
      <div id="header"></div>
      
     <!-- left__start -->
      <div id="left">
	      <jsp:include flush="true" page="/site/left.jsp"></jsp:include>
      </div>
      <!-- left__end -->
      
      
     <!-- right___start -->
      <div id="right">
      	  <h2>欢迎进入在线程序设计实验教学辅助系统</h2>
	      <div id="welcome" style="margin-left:30px;font-size: 15px;margin-top:70px">
	        <p>获取讲师上课的所有视频，笔记，代码！</p>
	        <p>获取最新的考试试题。</p>
	        <p>对课程中的疑虑进行提问并且可以获取到老师最专业的解答。</p>
	        <p>实验教学辅助系统祝您学习愉快！</p>
	        <br/><br/>
	      </div>
      </div>
      <!-- right___end -->
      
      
     <%@ include file="/site/footer.jsp" %>
  </div>
</body>
</html>
