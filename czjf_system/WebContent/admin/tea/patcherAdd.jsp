<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" charset="UTF-8"/>
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
        
        <link rel="stylesheet" type="text/css" href="/czjf_system/css/base.css" />
        <script type="text/javascript" src="/czjf_system/js/popup.js"></script>
       	<script type="text/javascript" src="/czjf_system/js/jquery.min.js"></script>
	</head>

	<body leftmargin="2" topmargin="9" background='/czjf_system/images/allbg.gif'>
			<form id="fm" action="${pageContext.request.contextPath}/TeacherServlet?method=patherAddTeacher" method="post" enctype="multipart/form-data">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="/czjf_system/images/wbg.gif" class='title'><span>&nbsp;</span></td>
					    </tr>
					    <input type="text" name="cNum" id="cNum" size="60" value="template" style="display:none"/>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        资料名称：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="docName" id="docName" size="60" placeholder="工学院计算机系教师表"/>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        模板下载：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						         <a href="${pageContext.request.contextPath}/DocumentServlet?method=downloadDocument&id=8" style="font-size: 13px;">
						             批量上传教师模板
						         </a>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        附件上传：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						         <input type="file" name="attachmentOldName" id="attachmentOldName" size="60"/>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        &nbsp;
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						       <input type="button" value="提交" onclick="check()"/>&nbsp; 
						       <input type="reset" value="重置"/>&nbsp;
						    </td>
						</tr>
					 </table>
			</form>
   </body>
 <script>
 $(function(){
	   //页面加载完毕   
	   //按键盘enter键触发提交按钮
	   $('#checkCode').focus(function(){
	 	    document.onkeydown = function (event) {
	 	        if (event && event.keyCode == 13) {
	 	        	$("#btnLogin").click();
	 	        }
	 	    }
	 	});

	});  	
 
	function check(){
		//获取资料标题，介绍，非空检验
		var name=$("#docName").val();		
		//截取空格
		var nm=$.trim(name);
		
		if(null==nm||""==nm){
			alert("资料名称不能为空");
			return;    //如果return false 不会对表单进行提交
		}
		//向服务端发起请求
  		document.getElementById("fm").submit();
	}
	

</script> 
</html>
