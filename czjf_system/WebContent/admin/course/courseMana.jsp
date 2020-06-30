<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
        <script language="javascript">
           function delCourse(cid)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="${pageContext.request.contextPath}/CourseServlet?method=delCourseById&id="+cid;
               }
           }
           
           function stuAdd()
           {
                 var url="${pageContext.request.contextPath}/StuServlet?method=addStudentUI";
				 window.location.href=url;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='/czjf_system/images/allbg.gif'>
			<table class="table table-bordered table-hover" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="8" background="/czjf_system/images/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="4%">序号</td>
					<td width="11%">课程编号</td>
					<td width="11%">课程名称</td>
					<td width="11%">教师编号</td>
					<td width="11%">教师姓名</td>
					<td width="11%">操作</td>
		        </tr>
		    
		        <c:forEach items="${page.list}" var="course"  varStatus="status">   
					<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						<td bgcolor="#FFFFFF" align="center">
							${status.index+1}
						</td>
						<td bgcolor="#FFFFFF" align="center">
							${course.cNum}
						</td>
						<td bgcolor="#FFFFFF" align="center">
							${course.cName}
						</td>
						<td bgcolor="#FFFFFF" align="center">
						    ${course.teaNum}
						</td>
						
						<td bgcolor="#FFFFFF" align="center">
						    ${course.teaRealName}
						</td>
						<td bgcolor="#FFFFFF" align="center">
							<a href="#" onclick="delCourse(${course.cId})" class="pn-loperator">删除</a>
						</td>
					</tr>
				</c:forEach>
				 
			</table>
			
			<%@ include file="/jsp/pageFile.jsp" %>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 8px;">
			  <tr>
			    <td>
			      <a href="${pageContext.request.contextPath}/CourseServlet?method=patherAddCourseUIByAdmin">
			      	<input type="button" value="批量添加课程" style="width: 100px;"/>
			      </a>
			    </td>
			  </tr>
		    </table>
	</body>

</html>
