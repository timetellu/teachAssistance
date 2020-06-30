package cn.itcast.czjf.web.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class StudentFilter implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {}
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		Object obj = req.getSession().getAttribute("stu");
		String uri = req.getRequestURI();
		if(uri.contains("/site/index.jsp")) {
			chain.doFilter(request, response);
		}else {
			if(null==obj) {
				//如果当前的session中没有登录成功的学生信息，转发到提示页面
				req.setAttribute("msg", "同学，请登录之后再做操作");
				req.getRequestDispatcher("/common/msg2.jsp").forward(req, response);
				return;
			}else {
				//如果当前的session中有登录成功的学生信息，放行
				chain.doFilter(request, response);
			}
		}
	}
}
