package cn.itcast.czjf.web.servlets;

import java.io.File;
import java.io.IOException;
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

import cn.itcast.czjf.domain.Course;
import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.domain.Student;
import cn.itcast.czjf.domain.Teacher;
import cn.itcast.czjf.service.CourseService;
import cn.itcast.czjf.service.DocumentService;
import cn.itcast.czjf.service.StuService;
import cn.itcast.czjf.service.TeacherService;
import cn.itcast.czjf.utils.PageModel;
import cn.itcast.czjf.utils.PatcherAddCourse;
import cn.itcast.czjf.utils.PatcherAddTeacher;
import cn.itcast.czjf.utils.UploadUtils;
import cn.itcast.czjf.web.base.BaseServlet;

/**
 * Servlet implementation class CourseServlet
 */
public class CourseServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//findCoursesWithPage（admin）
    public String findCoursesWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//获取当前页
		int currentNum=Integer.parseInt(request.getParameter("num"));
		//调用业务层功能，返回PageModel对象
		CourseService CourseService=new CourseService();
		PageModel pm=CourseService.findCoursesWithPage(currentNum);
		//将PageModel对象放入request
		request.setAttribute("page", pm);
		//转发到/admin/course/courseMana.jsp
    	return "/admin/course/courseMana.jsp";
    }
    
  //findCoursesWithPageByStudent（student）
    public String findCoursesWithPageByStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//1_接受当前页参数
		Student stu = (Student) request.getSession().getAttribute("stu");
		if(null == stu) {
			request.setAttribute("msg", "同学，请登录之后再做操作");
			return "/common/msg2.jsp";
		}
		int sId = stu.getStuId();
		int num =Integer.parseInt(request.getParameter("num"));
		
		//2_调用业务层功能，返回PageModel
		CourseService CourseService=new CourseService();
		PageModel pm = CourseService.findCoursesWithPageByStudent(num,sId);
		//将PageModel对象放入request
		request.setAttribute("page", pm);
		//转发到/site/course/courseAll.jsp
    	return "/site/course/courseAll.jsp";
    }
    
    //findCourseById（student）
    public String findCourseById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	int cId =Integer.parseInt(request.getParameter("id"));
		//1_调用业务层功能，返回存储着Course对象
    	CourseService CourseService=new CourseService();
		Course cour = CourseService.findCourseById(cId);  //通过id查询资料详情
		//2_将集合放入request域对象内
		request.setAttribute("cour", cour);
    	//3_转发到/site/course/courseDetail.jsp
		return "/site/course/courseDetail.jsp";
		
	}
    
   //addCourseUI
    public String addCourseUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	return "/admin/course/courseAdd.jsp";
    }
    
    //addCourse
    public String addCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//接受参数
    	String cNum = request.getParameter("cNum");
    	String cName = request.getParameter("cName");
    	String teaNum = request.getParameter("teaNum");
    	
    	Course course = new Course();
    	course.setcNum(cNum);
    	course.setcName(cName);
    	course.setTeaNum(teaNum);
    	
		CourseService CourseService=new CourseService();
		CourseService.addCourse(course);
		//重定向到查询课程信息功能
		response.sendRedirect("/czjf_system/CourseServlet?method=findCoursesWithPage&num=1");
		return null;
    }
    
    //delCourseById
    public String delCourseById(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//获取待删除课程的id
		String id=request.getParameter("id");
		//调用业务层删除课程功能
		CourseService CourseService=new CourseService();
		CourseService.delCourseById(id);
		//重定向到查询课程信息功能
		response.sendRedirect("/czjf_system/CourseServlet?method=findCoursesWithPage&num=1");
		return null;
    }
    
    //校验课程编号是否存在
    public String validateCourseExist(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//获取到页面提交到服务端的数据
    	String cNum=request.getParameter("cNum");
   	    //调用业务层功能：验证课程是否已经存在，返回课程对象
    	CourseService CourseService=new CourseService();
		Course course = CourseService.validateCourseExist(cNum);
    	if(null==course) {
    		//仓库中不存在当前课程编号
    		response.getWriter().print("no");
    	}else {
    		//仓库中存在
    		response.getWriter().print("yes");
    	}
    	//由于本次功能是由ajax发起的，不需要进行转发
    	return null;
    	
    }
    
    //校验所属老师的课程编号是否存在
    public String validateCourseExistByteacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Teacher tea = (Teacher) request.getSession().getAttribute("teacher");
    	int teaId = tea.getTeaId();
    	//获取到页面提交到服务端的数据
    	String cNum=request.getParameter("cNum");
   	    //调用业务层功能：验证课程是否已经存在，返回课程对象
    	CourseService CourseService=new CourseService();
		Course course = CourseService.validateCourseExistByteacher(cNum,teaId);
    	if(null==course) {
    		//仓库中不存在当前课程编号
    		response.getWriter().print("no");
    	}else {
    		//仓库中存在
    		response.getWriter().print("yes");
    	}
    	//由于本次功能是由ajax发起的，不需要进行转发
    	return null;
    }
    
  //patherAddCourseUIByAdmin
    public String patherAddCourseUIByAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/admin/course/patcherAdd.jsp";
    }
    
    //patherAddCourse（admin）
    public String patherAddCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//页面下载模板：DocumentServlet?method=downloadDocument
    	//页面上传文档，重新实现 addDocument方法
    	Map<String,String> map=new HashMap<String,String>();//携带表单名称以及表单参数
		Document document=new Document(); //携带Document数据，向 service,dao进行传递

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
			//5_判断当前FileItem是普通项还是上传项？
			//什么是普通项：表单中的普通字段，非上传字段
			//什么是上传项：表单中包含file组件上传项，携带着上传到服务端文件
			//item.isFormField() 判断当前的item是否是表单项目
			if(item.isFormField()) {
				//普通项
				//如果是普通项：获取到对应的表单名称和表单内容     Eg: docName<__>333333333 
				String name=item.getFieldName();
				String value=new String(item.getString().getBytes("ISO8859-1"),"utf-8");  //必须进行转码，不然会出现中文乱码
				map.put(name, value);
			}else {
				//如果是上传项：在服务端指定目录/upload/ 创建一个文件，将上传项中文件的二进制数据输出到创建好的文件中
				//获取到文件名称
				String fName=item.getName();
				//获取服务端upload真实路径
				realPath=getServletContext().getRealPath("/upload/");
				uuidName=UploadUtils.getUUIDName(fName);
				//XXXXXX.doc
				//在服务端指定路径下创建文件
				File f=new File(realPath,uuidName);
				if(!f.exists()) {
					f.createNewFile();
					//创建文件此时其中没有内容
				}
				item.write(f);//将上传到服务端的文件中的二进制数据输出到文件中
				map.put("docAttachment", uuidName);
				map.put("attachmentOldName", fName);
			}
		}
		//将MAP中的数据封装在Document对象上
		BeanUtils.populate(document, map);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		document.setUploadTime(sdf.format(new Date()));
		document.setDel("no");
		document.setStatus(1);
		//6_将普通项的数据以及文件的位置传递到service,dao.进行数据的保存
		DocumentService DocumentService=new DocumentService();
		DocumentService.addDocument(document);
    	//_获取上传文件的存储路径，File path = new File(filePath);
		String filePath = realPath+uuidName;
        File path = new File(filePath);
        PatcherAddCourse.readExcel(path);
    	//4_重定向到查询教师信息功能
		response.sendRedirect("/czjf_system/CourseServlet?method=findCoursesWithPage&num=1");
		return null;
    }
    
    
}
