package cn.itcast.czjf.web.servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.czjf.domain.Console;
import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.service.ProgramService;
import cn.timetell.stringCompiler.CompilerUtil;
import cn.timetell.stringCompiler.RunInfo;
import cn.itcast.czjf.utils.FileNameFilterUtil;
import cn.itcast.czjf.utils.JavaWebToken;
import cn.itcast.czjf.utils.UploadUtils;
import cn.itcast.czjf.web.base.BaseServlet;

public class ProgramServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//点击左侧 “在线编程”功能栏跳转的UI
	public String uploadProgram(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/site/program/program_upload.jsp";

	}
	//点击program_upload.jsp中的“输入代码”功能跳转的UI
	public String inputProgram(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/site/program/program_input.jsp";
	}
	
	
	//实现：输入代码在线运行
	public String runInputCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InstantiationException, InterruptedException {
		String inputCode = request.getParameter("code");
		String systemIn = request.getParameter("systemIn");
		
		//把用户从键盘输入的数据存储到文件中
		BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File("F:\\eclipse\\workspace\\czjf_system\\WebContent\\inputData.txt")));
            bw.write(systemIn);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		//接收客户端请求携带的token，进行校验（token转化为原数据）
//		String token = request.getParameter("token");
//      Map<String, Object> myTokenMap = JavaWebToken.parserJavaWebToken(token);
    
		ProgramService ProgramService = new ProgramService();
		String inputResult = ProgramService.runUploadCode(inputCode);
        
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(inputResult);
		return null;   //ajax方式提交
	}
	
	//实现：上传文件在线运行
	public String runUploadCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//一_实现文件上传
		Map<String,String> map=new HashMap<String,String>();//携带表单名称以及表单参数	
		//1_创建DiskFiletemFactory对象, 设置允许上传文件的大小
		DiskFileItemFactory fac = new DiskFileItemFactory();
		fac.setSizeThreshold(1024*1024*20); //允许上传文件的最大为20MB
		//2_创建ServletFileUpload upload
		ServletFileUpload upload = new ServletFileUpload(fac);
		//3_通过upload解析request,得到集合<FileItem>
		// FileItem代表什么？工具就将请求体中每对分割线中间的内容封装为一个FileItem对象
		List<FileItem> list = upload.parseRequest(request);
		String realPath = null;
		String uuidName = null;
		//4_遍历集合
		for (FileItem item : list) {
			//在服务端指定目录/upload/ 创建一个文件，将上传项中文件的二进制数据输出到创建好的文件中
			//获取到文件名称
			String fName=item.getName();
			//获取服务端upload真实路径
			realPath=getServletContext().getRealPath("/upload/");
			uuidName=UploadUtils.getUUIDName(fName);   //格式：B1DC9F0276F24017B9EB23B1D7BA5E42.txt
			//三_文件名过滤 
			//如果文件名不合法，直接return error页面，不上传到服务器
			FileNameFilterUtil fileNameFilterUtil = new FileNameFilterUtil();
	        File dir = new File(realPath);
	        boolean filter_res = fileNameFilterUtil.accept(dir, uuidName);
			//3_1文件名不合法
	        if(!filter_res) {
	        	return "/site/program/uploading_dialog_fail.jsp";
	        }
			//在服务端指定路径下创建文件
			File f=new File(realPath,uuidName);
			if(!f.exists()) {
				f.createNewFile();
				//创建文件此时其中没有内容
			}
			item.write(f);//将上传到服务端的文件中的二进制数据输出到文件中
			map.put("fileAttachment", uuidName);  //重新使用UUID命名的文件名（也是服务器realPath中存储的文件名）
			map.put("attachmentOldName", fName);  //原文件名
		}
		
		//二_文件恶意性检测（调用api，先不做）
		
		//3_2 文件名合法，读取文件内容，保存到一个字符串中
		String attachmentOldName = map.get("attachmentOldName");
        String filePath = realPath+uuidName;
        System.out.println(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"GBK"));
        char[] data=new char[1024*1024];
        int len=0;
        String uploadCode = null;
        while((len=br.read(data))!=-1) //读取文件并把它存入buf中，用num返回读到字符的个数，一直读到结尾
        {
        	uploadCode = new String(data, 0, len);
        }
        br.close(); 
//        System.out.println(uploadCode);
		//四_调用service层，将字符串提交到  “输入代码在线运行”util中
		ProgramService ProgramService = new ProgramService();
		String uploadResult = ProgramService.runUploadCode(uploadCode);
		//五_封装数据到javabean，然后设置session，传入console对象
		Console console = new Console();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		console.setAttachmentOldName(attachmentOldName);
		console.setJdkVersion("JDK 1.1.8");
		console.setServerVersion("Apache-tomcat-8.5.31");
		console.setMysqlVersion("MySQL-8.0.15");
		console.setSubmitTime(sdf.format(new Date()));
		console.setRunResult(uploadResult);
		request.getSession().setAttribute("console", console);
		//六_将结果返回客户端
		return "/site/program/upload_result.jsp";
	}


}

