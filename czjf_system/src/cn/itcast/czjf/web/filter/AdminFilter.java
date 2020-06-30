package cn.itcast.czjf.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class AdminFilter implements Filter {
	public void destroy() {}
	public void init(FilterConfig fConfig) throws ServletException {}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		Object obj = req.getSession().getAttribute("admin");
		if(null==obj) {
			//如果当前的session中没有登录成功的admin,转发到提示页面，请管理员登录之后再做操作
			req.setAttribute("msg", "请管理员登录之后再做操作");
			req.getRequestDispatcher("/common/msg.jsp").forward(req, response);;
			return;
			
		}else {
			//如果当前的session,中有登录成功的admin，放行
			chain.doFilter(request, response);
		}
	}
}
