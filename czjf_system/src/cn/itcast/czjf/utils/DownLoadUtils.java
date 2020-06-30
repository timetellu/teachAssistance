package cn.itcast.czjf.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

public class DownLoadUtils {
	public static String base64EncodeFileName(String fileName) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		try {
			return "=?UTF-8?B?"
					+ new String(base64Encoder.encode(fileName
							.getBytes("UTF-8"))) + "?=";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void setConentType(HttpServletRequest request,String fileName,HttpServletResponse response) throws UnsupportedEncodingException{
		//获取User-Agent请求头
		String agent=request.getHeader("User-Agent");
		//如果用户是在用火狐浏览器
		if(agent.contains("Firefox")){
			//为文件名进行 base64编码，让火狐浏览器识别
			fileName=DownLoadUtils.base64EncodeFileName(fileName);
		}else{
			//其他浏览器，用URLEncodeer进行编码
			fileName=URLEncoder.encode(fileName,"utf-8");
		}
		//设置一对响应头，目的让浏览器弹出另存为/保存对话框
		response.setHeader("Content-disposition", "attachment;filename="+fileName);
	}
}
