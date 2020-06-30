<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <base target="_self"/>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
        <link rel="stylesheet" type="text/css" href="/czjf_system/css/base.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        
	</head>

	<body leftmargin="2" topmargin="9" background='/czjf_system/images/allbg.gif'>
			<form id="fm" action="${pageContext.request.contextPath}/StuServlet?method=addStudent" name="formAdd" method="post">
			     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
					<tr bgcolor="#EEF4EA">
				        <td colspan="3" background="/czjf_system/images/wbg.gif" class='title'><span>&nbsp;</span></td>
				    </tr>
					<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					    <td width="25%" bgcolor="#FFFFFF" align="right">
					         学号：
					    </td>
					    <td width="75%" bgcolor="#FFFFFF" align="left">
					        <input type="text" name="stuNum" size="20"/>
					    </td>
					</tr>
					<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					    <td width="25%" bgcolor="#FFFFFF" align="right">
					        姓名：
					    </td>
					    <td width="75%" bgcolor="#FFFFFF" align="left">
					         <input type="text" name="stuRealname" size="20"/>
					    </td>
					</tr>
					<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					    <td width="25%" bgcolor="#FFFFFF" align="right">
					        性别：
					    </td>
					    <td width="75%" bgcolor="#FFFFFF" align="left">
					         <input type="radio" name="stuSex" value="男" checked="checked"/>男
					         &nbsp;&nbsp;
					         <input type="radio" name="stuSex" value="女"/>女
					         
					    </td>
					</tr>
					<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					    <td width="25%" bgcolor="#FFFFFF" align="right">
					        年龄：
					    </td>
					    <td width="75%" bgcolor="#FFFFFF" align="left">
					         <input type="text" name="stuAge" size="20"/>
					    </td>
					</tr>
					<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					    <td width="25%" bgcolor="#FFFFFF" align="right">
					        密码：
					    </td>
					    <td width="75%" bgcolor="#FFFFFF" align="left">
					         <input type="text" name="loginPw" size="20" value="000000"/>
					    </td>
					</tr>
					<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					    <td width="25%" bgcolor="#FFFFFF" align="right">
					       课程编号：
					    </td>
					    <td width="75%" bgcolor="#FFFFFF" align="left">
					         <input type="text" name="cNum" id="cNum" size="20" value="000000"/>
					    </td>
					</tr>
					<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					    <td width="25%" bgcolor="#FFFFFF" align="right">
					        &nbsp;
					    </td>
					    <td width="75%" bgcolor="#FFFFFF" align="left">
					       <input type="button" value="提交" onclick="check()"/>&nbsp; 
					       <input type="button" value="取消" onclick="quxiao()"/>&nbsp;
					    </td>
					</tr>
				 </table>
			</form>
   </body>
    
   <script language="javascript">
   $(function(){
	   //页面加载完毕
	   
	   //获取id为cNum的文本框，为其绑定失去焦点事件
	   $("#cNum").blur(function(){
	 	  //获取到管理员输入的课程编号信息，如果为空提示请录入
	 	  var cN=$("#cNum").val();
	 	  var cNum=$.trim(cN);
	 	  if(null!=cNum&&""!=cNum){
	 		//向服务端发起ajax请求，将管理员输入的教师编号信息发送到服务端
	 		$.post("${pageContext.request.contextPath}/CourseServlet",{"method":"validateCourseExist","cNum":cNum},function(data){
	 			//打印服务端响应回客户端的数据
	 			console.log(data);	 						
	 			if(data=="no"){
	 				alert("不存在此课程编号, 请重新录入");
	 			}
	 		});
	 	  }else{
	 		  //如果为空提示请录入学号
	 		  alert("请输入选课课程编号");
	 	  }
	 	  
	   });
	   
	   //按键盘enter键触发提交按钮
	   $('#checkCode').focus(function(){
	 	    document.onkeydown = function (event) {
	 	        if (event && event.keyCode == 13) {
	 	        	$("#btnLogin").click();
	 	        }
	 	    }
	 	});
  
	});  
	 
     function check()
     {
         if(document.formAdd.stuNum.value=="")
         {
             alert("请输入学号");
             return false;
         }
         if(document.formAdd.stuRealname.value=="")
         {
             alert("请输入姓名");
             return false;
         }
         
      //向服务端发起请求
   	 document.getElementById("fm").submit();
     }
     
     function quxiao()
     {
         window.close();
     }
         
   </script>
</html>
