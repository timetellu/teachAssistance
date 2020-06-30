package cn.itcast.czjf.web.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TeacherFilter implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {}
	public void destroy() {}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		Object obj = req.getSession().getAttribute("teacher");
		if(null==obj) {
			//如果当前的session中没有登录成功的老师信息，转发到提示页面
			req.setAttribute("msg", "请老师登录之后再做操作");
			req.getRequestDispatcher("/common/msg.jsp").forward(req, response);
			return;
		}else {
			//如果当前的session中有登录成功的老师信息，放行
			chain.doFilter(request, response);
		}	
	}
}
