package cn.itcast.czjf.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import cn.itcast.czjf.web.base.XSSHttpServletRequestWraper;
 
public class XSSFilter implements Filter {
	private static Logger logger = Logger.getLogger(XSSFilter.class);
	private FilterConfig config = null;
	private static String validate = "--|'";
	private String ignoreUrl = "";
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		validate = config.getInitParameter("validate");
		ignoreUrl = config.getInitParameter("ignoreUrl");
	}
 
	@Override
	public void destroy() {
		config = null;
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		XSSHttpServletRequestWraper wrapper = new XSSHttpServletRequestWraper((HttpServletRequest) request);
		String comKey = null;
		String comStr = null;
		String[] arrStr = null;
		String checkRs = null;
		String method = ((HttpServletRequest) wrapper).getMethod();
		/**
		 * 拦截get请求
		 */
		if (method != null && "get".compareToIgnoreCase(method) == 0) {
			chain.doFilter(wrapper, response);
			return;
		} 
		/**
		 * 拦截POST请求
		 */
		else {
			checkRs = null;
			/**
			 * 如果是白名单就直接跳过
			 */
			if (isIgnoreUrl(((HttpServletRequest) wrapper).getRequestURL().toString().toLowerCase())) {
				chain.doFilter(wrapper, response);
				return;
			}
			Map allFormDateMap = (Map) wrapper.getParameterMap(); // 获取表单提交过来的数据
			for (Iterator i = allFormDateMap.entrySet().iterator(); i.hasNext();) {
				Map.Entry entry = (Map.Entry) i.next();
				Object ob = (Object) entry.getValue();
				Object key = (Object) entry.getKey();
				/**
				 * 验证表单的key
				 */
				if (key instanceof String) {
					comKey = (String) key;
					if(comKey.equals("content"))continue; //如果是公告管理的内容字段content，则跳过过滤
					checkRs = dataAutoCheck(comKey);
				}
				/**
				 * 验证表单的value
				 */
				if (ob != null && ob instanceof String) {
					comStr = (String) ob;
					checkRs = dataAutoCheck(comStr);
				} else if (ob != null && ob instanceof String[]) {
					arrStr = (String[]) ob;
					for (int j = 0; j < arrStr.length; j++) {
						comStr = arrStr[j];
						checkRs = dataAutoCheck(comStr);
						if (!"RSRIGHT".equals(checkRs))
							break;
					}
				}
				if (!"RSRIGHT".equals(checkRs)) {
					 wrapper.getRequestDispatcher("/common/err_str_limite.jsp").forward(request,response);
//					response.setCharacterEncoding("utf-8");
//					PrintWriter out = response.getWriter();
//					Map<String, String> map = new HashMap<>();
//					map.put("errtype", "xss");
//					map.put("errinfo", "输入信息中包含非法数据！");
//					out.print(JSON.toJSONString(map));
//					out.close();
					return;
				}
			}
		}
		chain.doFilter(wrapper, response);
	}
 
	private boolean isIgnoreUrl(String url) {
		String param[] = ignoreUrl.split(",");
		for (int i = 0; i < param.length; i++) {
			if (url.indexOf(String.valueOf(param[i]).toLowerCase()) > 0) {
				return true;
			}
		}
		return false;
	}
 

	public static String dataAutoCheck(String str) throws UnsupportedEncodingException {
		Pattern p = null; // 正则表达式
		Matcher m = null; // 操作的字符串
		String[] sp = validate.split("\\|");
		for (int i = 0; i < sp.length; i++) {
			p = Pattern.compile(sp[i].trim().toLowerCase());
			m = p.matcher(str.toLowerCase());
			if (m.find())
				return sp[i].trim();
		}
		return "RSRIGHT";
	}
}

