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
			<form id="fm" action="${pageContext.request.contextPath}/DemoServlet?method=addDemo" method="post" enctype="multipart/form-data">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="/czjf_system/images/wbg.gif" class='title'><span>&nbsp;</span></td>
					    </tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        课程编号：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="cNum" id="cNum" size="60"/>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        DEMO名称：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="demoName" id="demoName" size="60"/>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        附件上传：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						         <input type="file" name="attachmentOldName" id="attachmentOldName" style="width: 370px;"  />
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
	   
	   //获取id为cNum的文本框，为其绑定失去焦点事件
	   $("#cNum").blur(function(){
	 	  //获取到管理员输入的课程编号信息，如果为空提示请录入
	 	  var cN=$("#cNum").val();
	 	  var cNum=$.trim(cN);
	 	  if(null!=cNum&&""!=cNum){
	 		//向服务端发起ajax请求，将管理员输入的教师编号信息发送到服务端
	 		$.post("${pageContext.request.contextPath}/CourseServlet",{"method":"validateCourseExistByteacher","cNum":cNum},function(data){
	 			//打印服务端响应回客户端的数据
	 			console.log(data);	 						
	 			if(data=="no"){
	 				alert("老师您好，您本学期不教学此课程, 请重新录入");
	 			}
	 		});
	 	  }else{
	 		  //如果为空提示请录入学号
	 		  alert("请录入选课课程编号");
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
 
	function check(){
		//获取资料标题，介绍，非空检验
		var name=$("#demoName").val();		
		//截取空格
		var nm=$.trim(name);
		
		if(null==nm||""==nm){
			alert("DEMO名称不能为空");
			return;    //如果return false 不会对表单进行提交
		}
		//向服务端发起请求
  		document.getElementById("fm").submit();
	}
	

</script> 
</html>
