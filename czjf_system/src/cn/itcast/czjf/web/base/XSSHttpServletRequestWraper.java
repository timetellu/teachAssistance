package cn.itcast.czjf.web.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.lang.StringUtils;
 
/**
 * Description: XSS防御请求处理类
 */
public class XSSHttpServletRequestWraper extends HttpServletRequestWrapper{
 
	public XSSHttpServletRequestWraper(HttpServletRequest request) {
		super(request);
	}
 
    /**
     *覆盖基类的getParameter()方法，对请求参数的值进行过滤。
     */
	@Override
    public String getParameter(String name) {
		String value = super.getParameter(name);  
		//Constants.MY_LOG.debug("getParameter----->转义处理");
		//return clearXss(super.getParameter(name));// 保留勿删
        if (null == value)  return null;  
        return xssEncode(value.trim());
    }
    
	/**
     *覆盖基类的getHeader()方法，对请求参数的值进行过滤。
     */
    @Override
    public String getHeader(String name) {
    	String value = super.getHeader(name);  
    	 //Constants.MY_LOG.debug("getHeader----->转义处理");
    	 //return clearXss(super.getHeader(name)); // 保留勿删
        if (null == value)  return null;  
        return xssEncode(value);
    }
    
    /**
     *覆盖基类的getParameterValues()方法，对请求参数的值进行过滤。
     */
    @Override
    public String[] getParameterValues(String name) {
    	//Constants.MY_LOG.debug("getParameterValues----->转义处理");
        if(!StringUtils.isEmpty(name)){
            String[] values = super.getParameterValues(name);
            if(values != null && values.length > 0){
                String[] newValues = new String[values.length];
                
                for(int i =0; i< values.length; i++){
                    //newValues[i] = clearXss(values[i]);// 保留勿删
                    newValues[i] = xssEncode(values[i]);
                }
                return newValues;
            }
        }
        return null;
    }
 
    @SuppressWarnings("unchecked")
	public Map<String,String[]> getParameterMap() {
		Map<String,String[]> map = new HashMap<String,String[]>();
		Map<String,String[]> paramMap = (Map<String,String[]>) super.getParameterMap();
        for (Iterator iterator = paramMap.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String,String[]> entry = (Map.Entry<String,String[]>) iterator.next();
            String [] values = entry.getValue();
            for (int i = 0; i < values.length; i++) {
                if(values[i] instanceof String){
                    values[i] = xssEncode(values[i]);
                }
            }
            //entry.setValue(values);//不支持tomcat7
            map.put(entry.getKey(), values);
        }
        return map;
    }
    
    /**
     *  
     * 处理字符转义【勿删，请保留该注释代码】
     * @param value
     * @return
    private String clearXss(String value){
        if (value == null || "".equals(value)) {
            return value;
        }
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replace("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replace("script", "");
        return value;
    }*/
    
    /** 
     * 将特殊字符替换为全角 
     * @param s 
     * @return 
     */  
    private  String xssEncode(String s) {  
        if (s == null || s.isEmpty()) {  
            return s;  
        }  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
            switch (c) {  
            //将特殊字符替换为全角
	        case '>':  
	            sb.append('＞');// 全角大于号    |\＞|\＜|\‘|\“|\＼|\／|\（|\）
	            break;  
	        case '<':  
	            sb.append('＜');// 全角小于号  
	            break;  
	        case '\'':  
	            sb.append('‘');// 全角单引号  
	            break;  
	        //case '\"':  
	        //    sb.append('“');// 全角双引号  
	        //    break;  
	        //case '&':  
	        //    sb.append('＆');// 全角＆  
	        //    break;  
	        case '\\':  
	            sb.append('＼');// 全角斜线  
	            break;  
	        case '/':  
	            sb.append('／');// 全角斜线  
	            break;  
	        //case '#':  
	        //    sb.append('＃');// 全角井号  
	        //    break;  
	        case '(':  
	            sb.append('（');// 全角(号  
	            break;  
	        case ')':  
	            sb.append('）');// 全角)号  
	            break;  
	        default:  
	            sb.append(c);  
	            break;  
	        }
        }  
        return sb.toString();  
    }  
}
