package cn.itcast.czjf.web.servlets;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.domain.Student;
import cn.itcast.czjf.domain.Teacher;
import cn.itcast.czjf.service.DocumentService;
import cn.itcast.czjf.service.StuService;
import cn.itcast.czjf.service.TeacherService;
import cn.itcast.czjf.utils.PageModel;
import cn.itcast.czjf.utils.PatcherAddStudent;
import cn.itcast.czjf.utils.PatcherAddTeacher;
import cn.itcast.czjf.utils.UploadUtils;
import cn.itcast.czjf.web.base.BaseServlet;

public class TeacherServlet extends BaseServlet {
	
	public String teacherLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取账户和密码
		String um=request.getParameter("userName");
		String up=request.getParameter("userPw");
		//调用业务层登录功能,   返回teacher对象
		TeacherService TeacherService=new TeacherService();
		Teacher teacher=TeacherService.teacherLogin(um,up);
		if(null==teacher) {
			//如果teacher对象为空， 登录失败，向request放入提示信息 ,转发到login.jsp页面
			request.setAttribute("msg", "账户密码不匹配");
			return "/login.jsp";
		}else {
			//如果teacher对象不为空 ,登录成功，向session放入teacher对象,重定向到/atea/index.jsp
			request.getSession().setAttribute("teacher", teacher);
			request.getSession().setMaxInactiveInterval(0);
			response.sendRedirect("/czjf_system/atea/index.jsp");
			return null;
		}
	}
	
	//findTeachersWithPage
	public String findTeachersWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取当前页
		int currentNum=Integer.parseInt(request.getParameter("num"));
		//调用业务层功能，返回PageModel对象
		TeacherService TeacherService=new TeacherService();
		PageModel pm=TeacherService.findTeachersWithPage(currentNum);
		//将PageModel对象放入request
		request.setAttribute("page", pm);
		//转发到/admin/tea/teaMana.jsp
		return "/admin/tea/teaMana.jsp";
	}
	
	//addTeacherUI
	public String addTeacherUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/admin/tea/teaAdd.jsp";
	}
	//addTeacher
	public String addTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受参数
		String teaNum=request.getParameter("teaNum");
		String teaRealName=request.getParameter("teaRealName");
		String teaSex=request.getParameter("teaSex");
		String teaAge=request.getParameter("teaAge");
		String loginName=request.getParameter("loginName");
		String loginPwd=request.getParameter("loginPwd");
		Teacher teacher=new Teacher();
		teacher.setTeaNum(teaNum);
		teacher.setTeaRealName(teaRealName);
		teacher.setTeaSex(teaSex);
		teacher.setTeaAge(teaAge);
		teacher.setLoginName(loginName);
		teacher.setLoginPwd(loginPwd);
		//调用业务层添加老师功能
		TeacherService TeacherService=new TeacherService();
		TeacherService.addTeacher(teacher);
		//重定向到查询老师信息功能
		response.sendRedirect("/czjf_system/TeacherServlet?method=findTeachersWithPage&num=1");
		return null;
	}
	//delTeacherById
	public String delTeacherById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取待删除老师的编号
		String id=request.getParameter("id");
		//调用业务层删除老师功能
		TeacherService TeacherService=new TeacherService();
		TeacherService.delTeacherById(id);
		//重定向到/TeacherServlet?method=findTeachersWithPage
		response.sendRedirect("/czjf_system/TeacherServlet?method=findTeachersWithPage&num=1");
		return null;
	}
	
	//findMyInfoUI
	public String findMyInfoUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return  "/atea/userinfo/userinfo.jsp";
	}
	
	//updateTeacher
	public String updateTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String num=request.getParameter("teaNum");
		String name=request.getParameter("teaRealname");
		String sex=request.getParameter("teaSex");
		String teaAge=request.getParameter("teaAge");
		String loginName=request.getParameter("loginName");
		String loginPwd=request.getParameter("loginPw");
		String teaId=request.getParameter("teaId");
		Teacher t=new Teacher();
		t.setTeaId(Integer.parseInt(teaId));
		t.setTeaNum(num);
		t.setTeaRealName(name);
		t.setTeaSex(sex);
		t.setTeaAge(teaAge);
		t.setLoginName(loginName);
		t.setLoginPwd(loginPwd);
		
		TeacherService TeacherService=new TeacherService();
		TeacherService.updateTeacher(t);
		request.getSession().setAttribute("teacher", t);
		return  "/atea/userinfo/msg.jsp";
	}
	
	//校验教师编号是否存在
    public String validateTeaExist(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//获取到页面提交到服务端的数据
    	String tn=request.getParameter("teaNum");
   	    //调用业务层功能：验证用户是否已经存在，返回学生对象
    	TeacherService TeacherService=new TeacherService();
    	Teacher tea = TeacherService.validateTeaExist(tn);
    	//根据返回的学生是否为空判断仓库中是否有已经存在的账户
    	if(null==tea) {
    		//仓库中不存在当前学号
    		response.getWriter().print("no");
    	}else {
    		//仓库中存在当前学号
    		response.getWriter().print("yes");
    	}
    	//由于本次功能是由ajax发起的，不需要进行转发
    	return null;
    	
    }
    
    
  //patherAddTeacherUIByAdmin
    public String patherAddTeacherUIByAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/admin/tea/patcherAdd.jsp";
    }
    
    //patherAddTeacher（admin）
    public String patherAddTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        PatcherAddTeacher.readExcel(path);
    	//4_重定向到查询教师信息功能
		response.sendRedirect("/czjf_system/TeacherServlet?method=findTeachersWithPage&num=1");
		return null;
    }
    

    
}












