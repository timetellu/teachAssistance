<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
        <title>实验教学辅助系统</title>
        <link rel="stylesheet" type="text/css" href="/czjf_system/css/base.css" /> 
        <link rel="stylesheet" href="/czjf_system/css/qiantai.css" type="text/css" charset="utf-8" />
        <script type="text/javascript" src="/czjf_system/js/public.js"></script>
	</head>

	<body leftmargin="2" topmargin="9" background='/czjf_system/img/allbg.gif'>
	<div id="wrapper">
		<div id="header"></div>
		<div id="left">
		 <jsp:include flush="true" page="/site/left.jsp"></jsp:include>
		</div>
		<div id="right">
			<h2>留言记录</h2>
			<form action="" name="formDetail" method="post">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="/czjf_system/img/wbg.gif" class='title'><span>&nbsp;</span></td>
					    </tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						          信息内容：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        ${message.content}
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						          发布时间：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        ${message.leaveWordTime}
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						          回复内容：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        ${message.replay}
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         回复时间：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        ${message.replayTime}
						    </td>
						</tr>
					 </table>
			</form>
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
