package cn.itcast.czjf.web.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.itcast.czjf.dao.DocumentDao;
import cn.itcast.czjf.domain.Console;
import cn.itcast.czjf.domain.Course;
import cn.itcast.czjf.domain.Demo;
import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.domain.Teacher;
import cn.itcast.czjf.service.CourseService;
import cn.itcast.czjf.service.DemoService;
import cn.itcast.czjf.service.DocumentService;
import cn.itcast.czjf.service.ProgramService;
import cn.itcast.czjf.utils.DownLoadUtils;
import cn.itcast.czjf.utils.PageModel;
import cn.itcast.czjf.utils.UploadUtils;
import cn.itcast.czjf.web.base.BaseServlet;

public class DemoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	//findDemosWithPageByTeacher
	public String findDemosWithPageByTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//1_接受当前页参数
		int num =Integer.parseInt(request.getParameter("num"));
		int tId = ((Teacher) request.getSession().getAttribute("teacher")).getTeaId();
		//2_调用业务层功能，返回PageModel
		DemoService DemoService = new DemoService();
		PageModel pm = DemoService.findDemosWithPageByTeacher(num,tId);
		//3_将PageModel放入request
		request.setAttribute("page", pm);
		//4_转发到 /atea/demo/demoMana.jsp
		return "/atea/demo/demoMana.jsp";
	}
	
	//addDemoUI
	public String addDemoUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		return "/atea/demo/demoAdd.jsp";
	}
	
	//addDemo
	public String addDemo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> map=new HashMap<String,String>();//携带表单名称以及表单参数
		Demo demo=new Demo(); //携带Demo数据，向 service,dao进行传递
		
		//1_创建DiskFiletemFactory对象, 设置允许上传文件的大小
		DiskFileItemFactory fac = new DiskFileItemFactory();
		fac.setSizeThreshold(1024*1024*20); //允许上传文件的最大为20MB
		//2_创建ServletFileUpload upload
		ServletFileUpload upload = new ServletFileUpload(fac);
		//3_通过upload解析request,得到集合<FileItem>
		// FileItem代表什么？工具就将请求体中每对分割线中间的内容封装为一个FileItem对象
		List<FileItem> list = upload.parseRequest(request);
		//4_遍历集合
		for (FileItem item : list) {
			//5_判断当前FileItem是普通项还是上传项？
			//什么是普通项：表单中的普通字段，非上传字段
			//什么是上传项：表单中包含file组件上传项，携带着上传到服务端文件
			//item.isFormField() 判断当前的item是否是表单项目
			if(item.isFormField()) {
				//普通项
				//如果是普通项：获取到对应的表单名称和表单内容     Eg: demoName<__>333333333 
				String name=item.getFieldName();
				String value=new String(item.getString().getBytes("ISO8859-1"),"utf-8");  //必须进行转码，不然会出现中文乱码
//				System.out.println(value);
				map.put(name, value);
			}else {
				//如果是上传项：在服务端指定目录/upload/ 创建一个文件，将上传项中文件的二进制数据输出到创建好的文件中
				//获取到文件名称
				String fName=item.getName();
//					System.out.println(fName);
				//获取服务端upload真实路径
				String realPath=getServletContext().getRealPath("/upload/");
//				System.out.println(realPath);
				String uuidName=UploadUtils.getUUIDName(fName);
				//XXXXXX.doc
				//在服务端指定路径下创建文件
				File f=new File(realPath,uuidName);
				if(!f.exists()) {
					f.createNewFile();
					//创建文件此时其中没有内容
				}
				item.write(f);//将上传到服务端的文件中的二进制数据输出到文件中
				map.put("demoAttachment", uuidName);
				map.put("attachmentOldName", fName);
			}
		}
		//将MAP中的数据封装在Document对象上
		BeanUtils.populate(demo, map);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		demo.setUploadTime(sdf.format(new Date()));
		demo.setDel("no");
		demo.setStatus(1);
		//6_将普通项的数据以及文件的位置传递到service,dao.进行数据的保存
		DemoService DemoService=new DemoService();
		DemoService.addDemo(demo);
		response.sendRedirect("/czjf_system/DemoServlet?method=findDemosWithPageByTeacher&num=1");
		return null;
	}
	
	//downloadDemo
	public String downloadDemo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//接受客户端资料的id
		int demoId = Integer.parseInt(request.getParameter("id"));
		//调用service功能，根据资料的id获取资料对象
		DemoService DemoService=new DemoService();
		Demo demo = DemoService.findDemoById(demoId);  //通过id查询资料详情
		//获取到项目下upload目录的绝对路径
		String realPath = getServletContext().getRealPath("/upload");
		//实例化一个File代表，代表待下载的资料
		File file=new File(realPath,demo.getDemoAttachment());

		DownLoadUtils.setConentType(request, demo.getDemoAttachment(), response);
		//通过response对象设置一对消息头
		//response.setHeader("Content-disposition", "attachment;filename="+doc.getDocAttachment());
		
		//通过File获取输入流
		InputStream is=new FileInputStream(file);
		//通过response获取到输出流
		OutputStream os = response.getOutputStream();
		//将输入流中的数据刷出到输出流中
		IOUtils.copy(is, os);
		IOUtils.closeQuietly(is);
		IOUtils.closeQuietly(os);
		
		//由于当前功能是在实现下载，是不需要转发到任意页面。数据直接从服务端的servlet通过response获取到的字节输出流将数据发送到客户端即可。
		return null;
	}
	
	//deleteDemoByTeacher
	public String deleteDemoByTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//1_获取待删除DEMO的编号
		int demoId =Integer.parseInt(request.getParameter("id"));
		//12_调用业务层功能，根据DEMO编号删除资料
		DemoService DemoService = new DemoService();
		DemoService.deleteDemoByTeacher(demoId);  //通过id查询到对应的DEMO信息
		//3_重定向到查询全部DEMO路径
		response.sendRedirect("/czjf_system/DemoServlet?method=findDemosWithPageByTeacher&num=1");
		return null;
	}
	
	//findDemosWithPageByCourse
	public String findDemosWithPageByCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//1_接受当前页参数
		int num =Integer.parseInt(request.getParameter("num"));
		int cId =Integer.parseInt(request.getParameter("cId"));
		//2_调用业务层功能，返回PageModel
		DemoService DemoService = new DemoService();
		PageModel pm = DemoService.findDemosWithPageByCourse(num,cId);
		//3_将PageModel放入request
		request.setAttribute("page", pm);
		//------查询课程名的过程
		//1_调用业务层功能，返回存储着Course对象
    	CourseService CourseService=new CourseService();
		Course cour = CourseService.findCourseById(cId);  //通过id查询资料详情
		//2_将集合放入request域对象内
		request.setAttribute("cour", cour);
		//4_转发到/site/demo/demoAll.jsp
		return "/site/demo/demoAll.jsp";
		
	}
	
	//findDemoById
	public String findDemoById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int demoId =Integer.parseInt(request.getParameter("id"));
		//1_调用业务层功能，返回存储着Demo对象
		DemoService DemoService = new DemoService();
		Demo demo = DemoService.findDemoById(demoId);  //通过id查询资料详情
		//2_将集合放入request域对象内
		request.setAttribute("demo", demo);
		//3_转发到/site/demo/demoDetail.jsp
		return "/site/demo/demoDetail.jsp";
	}
	
	//findDemoExerciseByCourse(传入cId)
	public String findDemoExerciseByCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//1_接受当前页参数
		int num =Integer.parseInt(request.getParameter("num"));
		int cId =Integer.parseInt(request.getParameter("cId"));
		//2_调用业务层功能，返回PageModel
		DemoService DemoService = new DemoService();
		List<Demo> list = DemoService.findDemoExerciseByCourse(num,cId);
//		System.out.println(list.toString());
		//3_将PageModel放入request
		request.setAttribute("list", list);
		
		//------查询课程名的过程
		//1_调用业务层功能，返回存储着Course对象
    	CourseService CourseService=new CourseService();
		Course cour = CourseService.findCourseById(cId);  //通过id查询资料详情
		//2_将集合放入request域对象内
		request.setAttribute("cour", cour);
		
		//-----根据课程查询对应demo总数的过程
		Demo demo = DemoService.findDemoRecordsByCourse(cId);
		String demoName = demo.getDemoName();
		int chapaterNums = Integer.parseInt(demoName.split("\\.")[0]);
		request.setAttribute("chapaterNums", chapaterNums);
		
		//4_转发到 /site/demo/demoExercise.jsp
		return "/site/demo/demoExercise.jsp";
	}
	
	//fillDemoToInput（点击代码连接，填充代码到代码输入框中）
//	public String fillDemoToInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InstantiationException {
//		int demoId =Integer.parseInt(request.getParameter("id"));
//		//1_调用业务层功能，返回存储着Demo对象
//		DemoService DemoService = new DemoService();
//		Demo demo = DemoService.findDemoById(demoId);  //通过id查询资料详情
//		String filePath = demo.getDemoAttachment();
//		String realPath=getServletContext().getRealPath("/upload/");
//		String fullPath = realPath+filePath;
//		int cId = demo.getcId();
//		int num=1;
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullPath),"GBK"));
//        char[] data=new char[1024*1024];
//        int len=0;
//        String demoCode = null;
//        while((len=br.read(data))!=-1) //读取文件并把它存入buf中，用num返回读到字符的个数，一直读到结尾
//        {
//        	demoCode = new String(data, 0, len);
//        }
//        br.close(); 
//		//四_将查询到的demo内容返回到客户端的textarea中
//		request.setAttribute("demoCode", demoCode);
//		//五_重新查询demo列表，回显到已有demo详情的页面
//		//2_调用业务层功能，返回PageModel
//		List<Demo> list = DemoService.findDemoExerciseByCourse(num,cId);
////				System.out.println(list.toString());
//		//3_将PageModel放入request
//		request.setAttribute("list", list);
//		
//		//------查询课程名的过程
//		//1_调用业务层功能，返回存储着Course对象
//    	CourseService CourseService=new CourseService();
//		Course cour = CourseService.findCourseById(cId);  //通过id查询资料详情
//		//2_将集合放入request域对象内
//		request.setAttribute("cour", cour);
//		
//		//-----根据课程查询对应demo总数的过程
//		Demo demo2 = DemoService.findDemoRecordsByCourse(cId);
//		String demoName = demo2.getDemoName();
//		int chapaterNums = Integer.parseInt(demoName.split("\\.")[0]);
//		request.setAttribute("chapaterNums", chapaterNums);
//		
//		//4_转发到 /site/demo/demoExercise.jsp
//		return "/site/demo/demoExercise.jsp";
//	}
	
	public String fillDemoToInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InstantiationException {
		int demoId =Integer.parseInt(request.getParameter("id"));
		//1_调用业务层功能，返回存储着Demo对象
		DemoService DemoService = new DemoService();
		Demo demo = DemoService.findDemoById(demoId);  //通过id查询资料详情
		String filePath = demo.getDemoAttachment();
		String realPath=getServletContext().getRealPath("/upload/");
		String fullPath = realPath+filePath;
		int cId = demo.getcId();
		int num=1;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullPath),"GBK"));
        char[] data=new char[1024*1024];
        int len=0;
        String demoCode = null;
        while((len=br.read(data))!=-1) //读取文件并把它存入buf中，用num返回读到字符的个数，一直读到结尾
        {
        	demoCode = new String(data, 0, len);
        }
        System.out.println(demoCode);
        br.close(); 
		//四_将查询到的demo内容返回到客户端的textarea中
        response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(demoCode);
		return null;
	}
	
}
