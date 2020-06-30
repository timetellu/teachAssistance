<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	
  </head>
  <body>
     <TABLE border=0 cellSpacing=3 cellPadding=3 width=178 height=22>
		 <TR>
		     <TD height=22 vAlign=middle width="100%">
		        <c:if test="${empty stu }">       
                  <form  action="${pageContext.request.contextPath}/StuServlet?method=stuLogin" name="ThisForm" method="post" id="fm">
			        <table cellspacing="0" cellpadding="0" width="98%" align="center" border="0" height="60">
			          <tr>
			            <td align="center" colspan="2" height="10">
			            	<span style="color:red" id="stuNumMsg">${msg}</span>
			            </td>
			          </tr>
			     
			          	<tr>
				            <td align="right" width="31%" height="30" style="font-size: 11px;">学&nbsp;&nbsp;&nbsp;号：</td>
				            <td align="left" width="69%">
				            	<input name="username" type="text" style="width: 100px;" id="username"/>
				            </td>
			          	</tr>
			          	<tr>
			            <td align="right" height="30" style="font-size: 11px;">密&nbsp;&nbsp;&nbsp;码：</td>
			            <td align="left">
			               <input type="password" style="width: 100px;" name="userPw" id="userPw" />
			             </td>
				          </tr>
				           <br/>
				          <tr>
				            <td align="right" height="30" style="font-size: 11px;" >验证码：</td>
				            <td align="left">
				               <input type="text" style="width: 100px;" name="checkCode" id="checkCode"  placeholder="请输入验证码" autocomplete="off"/>
				             </td>
				          </tr>

 					  <tr>
			            <td align="right" height="30" style="font-size: 11px;"></td>
			            <td align="left">
			               <a href="javascript:refreshCode()">
			               	<img src="${pageContext.request.contextPath}/CheckCodeServlet" title="看不清点击刷新" id="vcode"/>
			               </a>
			             </td>
			          </tr>
			      
			          <tr>
			            <td align="right" height="30" style="font-size: 11px;">&nbsp;</td>
			            <td align="left">
			             <br/> 
			                <input type="button" id="btnLogin" value="登录" style="border:#ccc 1px solid; background-color:#FFFFFF; font-size:12px;font-family: 微软雅黑;width: 50px;" />
			                <input type="reset" value="重置" style="border:#ccc 1px solid; background-color:#FFFFFF; font-size:12px;font-family: 微软雅黑;width: 50px;" />
			                <img id="indicator" src="/czjf_system/img/loading.gif" style="display:none;"/>
			            </td>
			          </tr>
			        </table>
				    </form>
			      </c:if>
			    	
				<c:if test="${not empty stu }">     
				    <br/>
				             欢迎您：${stu.stuRealname}同学 &nbsp;&nbsp;
				     <%--取消链接的默认行为 --%>
				    <a href="javascript:void(0)" onclick="logout()">安全退出</a> 
				    <img id="indicator1" src="/czjf_system/images/loading.gif" style="display:none"/>
				    <br/><br/><br/>
 				 </c:if>
             </TD>           
		 </TR>
	 </TABLE>
  </body>
  
<script>
function refreshCode() {
    var vcode = document.getElementById("vcode");
    vcode.src = "${pageContext.request.contextPath}/CheckCodeServlet?time="+new Date().getTime();
}

$(function(){
  //页面加载完毕
  
  //获取id为username的文本框，为其绑定失去焦点事件
  $("#username").blur(function(){
	  //获取到用户输入的学号信息，如果为空提示请录入学号
	  var um=$("#username").val();
	  var username=$.trim(um);
	  if(null!=username&&""!=username){
		//向服务端发起ajax请求，将用户录入的学号信息发送到服务端
		$.post("${pageContext.request.contextPath}/StuServlet",{"method":"validateUserExist","username":username},function(data){
			//打印服务端响应回客户端的数据
			console.log(data);
			if(data=="yes"){
				$("#stuNumMsg").html("");				
			}
						
			if(data=="no"){
				$("#stuNumMsg").html("不存在此户名,请重新录入");
			}
		});
		
		
	  }else{
		  //如果为空提示请录入学号
		  alert("请输入学号");
	  }
	  
  });
  
  
  //获取id为btnLogin登录按钮，为其绑定点击事件 
  $("#btnLogin").click(function(){
	  //校验用户名和密码不能为空
	  //获取用户名
	  var username=$("#username").val();
	  //获取密码
	  var password=$("#userPw").val();
	  //截取字符串
	  var um=$.trim(username);
	  var up=$.trim(password);
	  if(null==um||""==um){
		  alert("请输入正确的学号");
		  return false;
	  }
	  
	  if(null==up||""==up){
		  alert("请输入正确的密码");
		  return false;
	  }
	  
	  //利用JS实现登录
	  document.getElementById("fm").submit();
	  
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

function logout(){
	//用户确认提示
	if(confirm("确定要退出吗?")){
		location.href="${pageContext.request.contextPath}/StuServlet?method=stuLogout";
	}
}




</script>  
</html>
