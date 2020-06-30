<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="/czjf_system/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/czjf_system/bootstrap/css/bootstrap.min.css" />				
       
	</head>

	<body leftmargin="2" topmargin="2" background='/czjf_system/img/allbg.gif'>
			<table class="table table-bordered table-hover" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="7" background="/czjf_system/img/tbg.gif">&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="4%">序号</td>
					<td width="20%">课程名称</td>
					<td width="10%">标题</td>
					<td width="20%">附件</td>
					<td width="10%">是否公开</td>
					<td width="20%">操作</td>
		        </tr>
				
			 <c:forEach items="${page.list}" var="v"  varStatus="status">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${status.index+1}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${v.cName}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${v.vedioName}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="${pageContext.request.contextPath}/VedioServlet?method=downloadVedio&id=${v.vedioId}" style="font-size: 13px;">${v.attachmentOldName}</a>
			            &nbsp;&nbsp;&nbsp;
			            <a href="${pageContext.request.contextPath}/VedioServlet?method=playVedioById&id=${v.vedioId}"  class="pn-loperator" >播放</a>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<c:if test="${v.status ==1 }">
							是
						</c:if>
						<c:if test="${v.status ==0 }">
							否
						</c:if>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <%--取消链接的默认行为 --%>
						<a href="javascript:void(0)" onclick="delVedio(${v.vedioId})"  class="pn-loperator">删除</a>
						
					</td>
				</tr>
			  </c:forEach>
			
			</table>
			<%@ include file="/jsp/pageFile.jsp" %>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 8px;">
			  <tr>
			    <td>
			      <input type="button" value="添加教学视频" style="width: 120px;" onclick="vedioAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
<script>
function delVedio(vId){
	//做删除视频之前的确认提示
	if(confirm("确认删除当前的视频吗?")){
		//向服务端发起请求，将当前正在删除的视频的编号发送到服务端
		location.href="${pageContext.request.contextPath}/VedioServlet?method=deleteVedioByTeacher&id="+vId;
	}
}

function vedioAdd(){
	location.href = "${pageContext.request.contextPath}/VedioServlet?method=addVedioUI";
}
</script>	
</html>




