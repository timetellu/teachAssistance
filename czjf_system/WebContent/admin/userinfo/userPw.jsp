<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
        
        <link rel="stylesheet" type="text/css" href="/czjf_system/css/base.css" />
		
		<script type='text/javascript' src='/czjf_system/dwr/interface/loginService.js'></script>
        <script type='text/javascript' src='/czjf_system/dwr/engine.js'></script>
        <script type='text/javascript' src='/czjf_system/dwr/util.js'></script>
		
        <script language="javascript">
            function check()
            {
                 var userPwReal="${sessionScope.admin.userPw}";
                 if(document.formPw.userOldPw.value !=userPwReal)
                 {
                     alert("原密码不正确");
                     return ;
                 }
                 
                 if(document.formPw.userNewPw.value =="")
                 {
                     alert("新密码不能空");
                     return ;
                 }
                 
      			//提交form表单
                document.getElementById("fm").submit();
            }
            function callback(data)
            {
                document.getElementById("indicator").style.display="none";
                //alert("修改成功");
            }
        </script>
	</head>

	<body leftmargin="2" topmargin="9" background='/czjf_system/images/allbg.gif'>
			<form id="fm" method="post" action="${pageContext.request.contextPath}/AdminServlet?method=updateAdmin" name="formPw">
			<table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
				<tr bgcolor="#EEF4EA">
				    <td colspan="2" background="/czjf_system/images/wbg.gif" class='title'><span>&nbsp;</span></td>
				</tr>
				<tr>
					<input type="hidden" name="userId" value="${sessionScope.admin.userId }" />
				</tr>
				<tr bgcolor="#FFFFFF">
				    <td width="10%" bgcolor="#FFFFFF" align="right">
				         登录名：
				    </td>
				    <td width="90%" bgcolor="#FFFFFF">
				        <input type="text" value="${sessionScope.admin.userName }" name="userName" style="width: 250px;" disabled="disabled"/>
				    </td>
				</tr>
				<tr bgcolor="#FFFFFF">
				    <td width="10%" bgcolor="#FFFFFF" align="right">
				        原密码：
				    </td>
				    <td width="90%" bgcolor="#FFFFFF">
				        <input type="password" name="userOldPw" id="userOldPw" style="width: 250px;"/>
				    </td>
				</tr>
				<tr bgcolor="#FFFFFF">
				    <td width="10%" bgcolor="#FFFFFF" align="right">
				         新密码：
				    </td>
				    <td width="90%" bgcolor="#FFFFFF">
				        <input type="password" id="userNewPw" name="userNewPw" style="width: 250px;"/>
				    </td>
				</tr>
				<tr bgcolor="#FFFFFF">
				    <td width="10%" bgcolor="#FFFFFF" align="right">
				        &nbsp;
				    </td>
			        <td width="90%" bgcolor="#FFFFFF">
			             <input type="button" value="修改" onclick="check()"/>
			             &nbsp;&nbsp;&nbsp;
			             <input type="reset" value="重置"/>

			        </td>
				</tr>
			</table>
			</form>
   </body>
</html>
