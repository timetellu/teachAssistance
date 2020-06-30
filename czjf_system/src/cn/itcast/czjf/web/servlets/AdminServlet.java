package cn.itcast.czjf.web.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.czjf.domain.Admin;
import cn.itcast.czjf.service.AdminService;
import cn.itcast.czjf.web.base.BaseServlet;

public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	public String adminLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取账户和密码
		String um=request.getParameter("userName");
		String up=request.getParameter("userPw");
		//调用业务层登录功能，   返回Admin对象
		AdminService AdminService=new AdminService();
		Admin admin=AdminService.adminLogin(um,up);
		if(null==admin) {
			//如果Admin对象为空，  登录失败，向request放入提示信息转发到login.jsp页面
			request.setAttribute("msg", "账户和密码不匹配");
			return "/login.jsp";
		}else {
			//如果Admin对象不为空 登录成功，向session放入Admin对象重定向到/admin/index.jsp
			request.getSession().setAttribute("admin", admin);
			request.getSession().setMaxInactiveInterval(0);
			response.sendRedirect("/czjf_system/admin/index.jsp");
			return null;
		}
	}

	//修改登录密码
	public String updateAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userNewPw = request.getParameter("userNewPw");
		String userId = request.getParameter("userId");

		Admin a = new Admin();
		a.setUserId(Integer.parseInt(userId));
		a.setUserPw(userNewPw);
	
		AdminService AdminService=new AdminService();
		AdminService.updateAdmin(a);
		return  "/admin/userinfo/msg.jsp";
	}

}
