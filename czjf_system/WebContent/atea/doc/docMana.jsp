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
		<script language="JavaScript" src="/czjf_system/js/public.js" type="text/javascript"></script>
	</head>

	<body leftmargin="2" topmargin="2" background='/czjf_system/images/allbg.gif'>
			<table class="table table-bordered table-hover" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="13" background="/czjf_system/images/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="4%">序号</td>
					<td width="20%">课程名称</td>
					<td width="20%">资料名称</td>
					<td width="20%">附件</td>
					<td width="10%">是否公开</td>
					<td width="20%">操作</td>
		        </tr>
		        
		        <c:forEach items="${page.list}" var="d"  varStatus="status">
				<tr class="table table-bordered table-hover" align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${status.index+1}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${d.cName}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${d.docName}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="${pageContext.request.contextPath}/DocumentServlet?method=downloadDocument&id=${d.docId}" style="font-size: 13px;">${d.attachmentOldName}</a>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<c:if test="${d.status ==1 }">
							是
						</c:if>
						<c:if test="${d.status ==0 }">
							否
						</c:if>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <%--取消链接的默认行为 --%>
						<a href="javascript:void(0)" onclick="delDoc(${d.docId})"  class="pn-loperator">删除</a>
						
					</td>
				</tr>
			  </c:forEach>
			
		    </table>
			<%@ include file="/jsp/pageFile.jsp" %>
		        	
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 8px;">
			  <tr>
			    <td>
			      <input type="button" value="添加资料" style="width: 120px;" onclick="docAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
<script>
	function delDoc(docId){
		//做删除资料之前的确认提示
		if(confirm("确认删除当前的资料吗?")){
			//向服务端发起请求，将当前正在删除的资料的编号发送到服务端
			location.href="${pageContext.request.contextPath}/DocumentServlet?method=deleteDocumentByTeacher&id="+docId;
		}
	}
	
	function docAdd(){
		location.href = "${pageContext.request.contextPath}/DocumentServlet?method=addDocumentUI";
}
</script>	
</html>
