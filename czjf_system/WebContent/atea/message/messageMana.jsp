<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="/czjf_system/css/base.css" />
		
		<script language="JavaScript" src="/czjf_system/js/public.js" type="text/javascript"></script>
		
        <script language="javascript">
          
           function messageHuifu(id)
           {
               var strUrl = "/czjf_system/MessageServlet?method=findMessageById&id="+id;
               var ret = window.showModalDialog(strUrl,"","dialogWidth:700px; dialogHeight:400px; dialogLeft: status:no; directories:yes;scrollbars:yes;Resizable=no;");
               window.location.reload();
           }
        </script>
	</head>

	<body leftmargin="2" topmargin="2" background='/czjf_system/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="14" background="/czjf_system/img/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="4%">序号</td>
					<td width="10%">信息内容</td>
					<td width="8%">发布时间</td>
					<td width="10%">回复信息</td>
					<td width="8%">回复时间</td>
					<td width="8%">提问学生</td>
					<td width="8%">操作</td>
		        </tr>	
			<c:forEach items="${page.list}" var="msg"  varStatus="status"> 
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						 ${status.index+1}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${msg.content}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${msg.leaveWordTime }
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${msg.replay}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${msg.replayTime}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${msg.stuRealname}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <%--
						<input type="button" value="删除" onclick="messageDel('${msg.messageId}')"/>
						--%>
						<a href="${pageContext.request.contextPath}/MessageServlet?method=replayUI&id=${msg.messageId}" target="I2">回复</a>
						
					</td>
				</tr>
			  </c:forEach>
				
				</tr>
			</table>
			<%@ include  file="/jsp/pageFile.jsp" %>
	</body>
</html>
