<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 
    <script src="${pageContext.request.contextPath}/js/menu.js"></script>

	<style type="text/css">
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		.STYLE1 {
			color: #FFFFFF;
			font-weight: bold;
			font-size: 12px;
		}
		.STYLE2 {
			font-size: 12px;
			color: #03515d;
		}
		a:link {font-size:12px; text-decoration:none; color:#03515d;}
		a:visited{font-size:12px; text-decoration:none; color:#03515d;}
		.STYLE3 {font-size: 12px}
	</style>
  </head>

  <body>
     <table width="156"  border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center" valign="top">
	            <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
				      <tr>
				        <td height="33" background="${pageContext.request.contextPath}/images/main_21.gif">&nbsp;</td>
				      </tr>
				      <!-- caidan -->
				      <tr>
				        <td height="30" background="${pageContext.request.contextPath}/images/main_25.jpg" id="td1" onClick="show(1)">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          <tr>
					            <td width="13%">&nbsp;</td>
					            <td width="72%" height="20">
						              <div align="center">
							              <table width="100%" height="21" border="0" cellpadding="0" cellspacing="0">
							                <tr>
							                  <td><div align="center"><img src="${pageContext.request.contextPath}/images/top_8.gif" width="16" height="16"></div></td>
							                  <td valign="middle">
							                      <div align="center" class="STYLE1" style="font-family: 微软雅黑;">修改我的信息</div>
							                  </td>
							                </tr>
							               </table>
						              </div>
					            </td>
					            <td width="15%">&nbsp;</td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <tr id="show1" style="display:none">
				        <td align="center" valign="top">
					        <table border="0" align="center" cellpadding="5" cellspacing="5">
					          <tr>
					            <td><a href="${pageContext.request.contextPath}/TeacherServlet?method=findMyInfoUI" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;修改我的信息</a>
					            </td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <!-- caidan -->
				      <!-- caidan -->
				      <tr>
				        <td height="30" background="${pageContext.request.contextPath}/images/main_25.jpg" id="td2" onClick="show(2)">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          <tr>
					            <td width="13%">&nbsp;</td>
					            <td width="72%" height="20"><div align="center">
					              <table width="100%" height="21" border="0" cellpadding="0" cellspacing="0">
					                <tr>
					                  <td><div align="center"><img src="${pageContext.request.contextPath}/images/top_8.gif" width="16" height="16"></div></td>
					                  <td valign="middle">
					                      <div align="center" class="STYLE1" style="font-family: 微软雅黑;">教学资料管理</div>
					                  </td>
					                </tr>
					              </table>
					            </div></td>
					            <td width="15%">&nbsp;</td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <tr id="show2" style="display:none">
				        <td align="center" valign="top">
					        <table border="0" align="center" cellpadding="5" cellspacing="5">
					          <tr>
					            <td><a href="${pageContext.request.contextPath}/DocumentServlet?method=findDocumentsWithPageByTeacher&num=1" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;教学资料管理</a>
					            </td>
					          </tr>
					          <tr>
					            <td><a href="${pageContext.request.contextPath}/DocumentServlet?method=addDocumentUI"  target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;添加教学资料</a>
					            </td>
					          </tr>
					          <tr>
					            <td><a href="${pageContext.request.contextPath}/DocumentServlet?method=addDocumentUI"  target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;修改教学资料</a>
					            </td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <!-- caidan -->
				      <!-- caidan -->
				      <tr>
				        <td height="30" background="${pageContext.request.contextPath}/images/main_25.jpg" id="td3" onClick="show(3)">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          <tr>
					            <td width="13%">&nbsp;</td>
					            <td width="72%" height="20"><div align="center">
					              <table width="100%" height="21" border="0" cellpadding="0" cellspacing="0">
					                <tr>
					                  <td><div align="center"><img src="${pageContext.request.contextPath}/images/top_8.gif" width="16" height="16"></div></td>
					                  <td valign="middle">
					                      <div align="center" class="STYLE1" style="font-family: 微软雅黑;">教学视频管理</div>
					                  </td>
					                </tr>
					              </table>
					            </div></td>
					            <td width="15%">&nbsp;</td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <tr id="show3" style="display:none">
				        <td align="center" valign="top">
					        <table border="0" align="center" cellpadding="5" cellspacing="5">
					          <tr>
					            <td><a href="${pageContext.request.contextPath}/VedioServlet?method=findVediosWithPageByTeacher&num=1" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;教学视频管理</a>
					            </td>
					          </tr>
					          <tr>
					            <td><a href="${pageContext.request.contextPath}/VedioServlet?method=addVedioUI" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;上传教学视频</a>
					            </td>
					          </tr>
					          <tr>
					            <td><a href="${pageContext.request.contextPath}/VedioServlet?method=addVedioUI" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;修改教学视频</a>
					            </td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <!-- caidan -->
				      
			
				      <!-- caidan -->
				      <tr>
				        <td height="30" background="${pageContext.request.contextPath}/images/main_25.jpg" id="td4" onClick="show(4)">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          <tr>
					            <td width="13%">&nbsp;</td>
					            <td width="72%" height="20"><div align="center">
					              <table width="100%" height="21" border="0" cellpadding="0" cellspacing="0">
					                <tr>
					                  <td><div align="center"><img src="${pageContext.request.contextPath}/images/top_8.gif" width="16" height="16"></div></td>
					                  <td valign="middle">
					                      <div align="center" class="STYLE1" style="font-family: 微软雅黑;">课程源码管理</div>
					                  </td>
					                </tr>
					              </table>
					            </div></td>
					            <td width="15%">&nbsp;</td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <tr id="show4" style="display:none">
				        <td align="center" valign="top">
					        <table border="0" align="center" cellpadding="5" cellspacing="5">
					          <tr>
					            <td>
					               <a href="${pageContext.request.contextPath}/DemoServlet?method=findDemosWithPageByTeacher&num=1" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;查看DEMO列表</a>
					            </td>
					          </tr>
					          <tr>
					            <td>
					               <a href="${pageContext.request.contextPath}/DemoServlet?method=addDemoUI" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;添加DEMO信息</a>
					            </td>
					          </tr>
					           <tr>
					            <td>
					               <a href="${pageContext.request.contextPath}/DemoServlet?method=addDemoUI" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;修改DEMO信息</a>
					            </td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <!-- caidan -->
				 
				      
				      <!-- caidan -->
				      
				      <tr>
				        <td height="30" background="${pageContext.request.contextPath}/images/main_25.jpg" id="td5" onClick="show(5)">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          <tr>
					            <td width="13%">&nbsp;</td>
					            <td width="72%" height="20"><div align="center">
					              <table width="100%" height="21" border="0" cellpadding="0" cellspacing="0">
					                <tr>
					                  <td><div align="center"><img src="${pageContext.request.contextPath}/images/top_8.gif" width="16" height="16"></div></td>
					                  <td valign="middle">
					                      <div align="center" class="STYLE1" style="font-family: 微软雅黑;">学生信息管理</div>
					                  </td>
					                </tr>
					              </table>
					            </div></td>
					            <td width="15%">&nbsp;</td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <tr id="show5" style="display:none">
				        <td align="center" valign="top">
					        <table border="0" align="center" cellpadding="5" cellspacing="5">
					          <tr>
					            <td>
					              <%--
					              <a href="${pageContext.request.contextPath}/StudentServlet?method=findAllStudentsWithPage&num=1" target="I2" style="margin-left: 20px;">
					               --%>
					               <a href="${pageContext.request.contextPath}/StuServlet?method=findStudentsWithPageByTeacher&num=1" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;学生信息管理</a>
					            </td>
					          </tr>
					          <tr>
					            <td><a href="${pageContext.request.contextPath}/StuServlet?method=addStudentUIByTeacher" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;学生信息添加</a>
					            </td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <!-- caidan -->
				      
				      
				      <%--
				      <!-- caidan -->
				      <tr>
				        <td height="30" background="${pageContext.request.contextPath}/images/main_25.jpg" id="td5" onClick="show(5)">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          <tr>
					            <td width="13%">&nbsp;</td>
					            <td width="72%" height="20"><div align="center">
					              <table width="100%" height="21" border="0" cellpadding="0" cellspacing="0">
					                <tr>
					                  <td><div align="center"><img src="${pageContext.request.contextPath}/images/top_8.gif" width="16" height="16"></div></td>
					                  <td valign="middle">
					                      <div align="center" class="STYLE1" style="font-family: 微软雅黑;">实验作业管理</div>
					                  </td>
					                </tr>
					              </table>
					            </div></td>
					            <td width="15%">&nbsp;</td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <tr id="show5" style="display:none">
				        <td align="center" valign="top">
					        <table border="0" align="center" cellpadding="5" cellspacing="5">
					          <tr>
					            <td>
					               <a href="${pageContext.request.contextPath}/TaskServlet?method=findTasksWithPageByTeacher&num=1" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;查看作业信息</a>
					            </td>
					          </tr>
					          <tr>
					            <td>
					               <a href="${pageContext.request.contextPath}/TaskServlet?method=addTaskUI" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;发布作业信息</a>
					            </td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <!-- caidan -->
				       --%>
				      
				      <!-- caidan -->
				      <tr background="${pageContext.request.contextPath}/images/main_25.jpg" id="td7" onClick="show(7)">
				        <td height="30" background="${pageContext.request.contextPath}/images/main_25.jpg" id="td6" onClick="show(7)">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          <tr>
					            <td width="13%">&nbsp;</td>
					            <td width="72%" height="20"><div align="center">
					              <table width="100%" height="21" border="0" cellpadding="0" cellspacing="0">
					                <tr>
					                  <td><div align="center"><img src="${pageContext.request.contextPath}/images/top_8.gif" width="16" height="16"></div></td>
					                  <td valign="middle">
					                      <div align="center" class="STYLE1" style="font-family: 微软雅黑;">师生交流模块</div>
					                  </td>
					                </tr>
					              </table>
					            </div></td>
					            <td width="15%">&nbsp;</td>
					          </tr>
					        </table>
					    </td>
				      </tr>
				      <tr id="show7" style="display:none">
				        <td align="center" valign="top">
					        <table border="0" align="center" cellpadding="5" cellspacing="5">
					          <tr>
					            <td>
					            	<a href="${pageContext.request.contextPath}/MessageServlet?method=findMessagesWithPageByTeacher&num=1" target="I2" style="margin-left: 20px;">
					               <img src="${pageContext.request.contextPath}/images/arr3.gif" border="0">&nbsp;&nbsp;师生交流模块</a>
					            </td>
					          </tr>

					        </table>
					    </td>
				      </tr>
				      <!-- caidan -->
				      
	            </table>
            </td>
        </tr>
     </table>
  </body>
</html>

