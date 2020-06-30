package cn.itcast.czjf.web.servlets;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import cn.itcast.czjf.utils.JavaWebToken;
import cn.itcast.czjf.utils.PageModel;
import cn.itcast.czjf.utils.PatcherAddStudent;
import cn.itcast.czjf.utils.UploadUtils;
import cn.itcast.czjf.web.base.BaseServlet;

public class StuServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	//校验用户名是否存在
    public String validateUserExist(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//获取到页面提交到服务端的数据学号
    	String um=request.getParameter("username");
   	    //调用业务层功能：验证用户是否已经存在，返回学生对象
    	StuService stuService=new StuService();
    	Student stu=stuService.validateUserExist(um);
    	//根据返回的学生是否为空判断仓库中是否有已经存在的账户
    	if(null==stu) {
    		//仓库中不存在当前学号
    		response.getWriter().print("no");
    	}else {
    		//仓库中存在当前学号
    		response.getWriter().print("yes");
    	}
    	//由于本次功能是由ajax发起的，不需要进行转发
    	return null;
    	
    }

    //学员登录功能
    public String stuLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	/**
    	 * 校验验证码
    	 */
    	//1_获取用户填写的验证码
    	String checkCode = request.getParameter("checkCode");
    	//2_从session中获取CheckCodeServlet生成的验证码
    	String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
    	//移除服务端产生的, 存在于session中的验证码，确保验证码的一致性
    	session.removeAttribute("CHECKCODE_SERVER");   
    	
    	//3_忽略大小写比较客户端和服务端的验证码
    	/**
    	 *3_1 验证码错误，校验用户名和密码
    	 */
    	if(! checkcode_server.equalsIgnoreCase(checkCode)) {
    		//提示信息
            request.setAttribute("msg","验证码错误");
            //转发至登录页面
            return "/site/index.jsp";
    	}
    	//3_2 验证码正确
    	//获取 学号
    	String um=request.getParameter("username");
    	//获取 密码
    	String up=request.getParameter("userPw");
    	
    	//调用业务层登录功能，返回学生对象
    	StuService StuService=new StuService();
    	Student student=StuService.stuLogin(um,up);
    	if(null!=student) {
    		if(student.getStatus() >= 60) {
    			//如果返回的学生不为空，且信用分数合格，可以登录成功
    	        //-----向session中放入学生信息 
    			session.setAttribute("stu", student);
    			
    			//-----服务器生成token，返回给客户端
    			Map<String, Object> claims = new HashMap<String,Object>();
    	        //添加登录认证通过的用户数据
    	        claims.put("id", student.getStuId());
    	        //转成token
    	        String myToken = JavaWebToken.createJavaWebToken(claims);
//    	        System.out.println("我的token数据：" + myToken); //测试成功
    	        //设置到响应头中，返回给客户端
    	        session.setAttribute("token", myToken);
    	        //由于前后端存在跨域问题、后端需要设置一下支持跨域、并且针对OPTIONS请求特殊处理一下、还需要将filer优先级设置-1、优先执行跨域过滤器
    	        response.setHeader("Access-Control-Allow-Origin", "*");
    	        response.setHeader("Access-Control-Allow-Methods", "*");
    	        response.setHeader("Access-Control-Max-Age", "3600");
    	        response.setHeader("Access-Control-Allow-Headers", "*");
    	        if (request.getMethod().equals("OPTIONS")) {
    	            response.setStatus(HttpServletResponse.SC_OK);
    	            return null;
    	        }
    	        
    	        //转发到/site/index.jsp
        		return "/site/index.jsp";
    		}else {
    			//虽然返回的学生不为空，但是信用分数不合格，禁止其登录
    			request.setAttribute("msg", "您的信用分数不合格，被禁止登录系统");
    			return "/site/index.jsp";
    		}
    	}else {
        	//如果返回的学生空，说明登录失败，向request域对象放入提示信息，转发到/site/index.jsp，可能是信誉分数低于60
    		request.setAttribute("msg", "账号或密码有误");
    		return "/site/index.jsp";
    	}
    }
  
    
   //stuLogout
    public String stuLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//获取session,使其失效
    	request.getSession().invalidate();
    	//重新定向到首页
    	response.sendRedirect("/czjf_system/site/index.jsp");
    	return null;
    	
    }
    
    //findStudentsWithPage
    public String findStudentsWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//获取当前页
		int currentNum=Integer.parseInt(request.getParameter("num"));
		//调用业务层功能，返回PageModel对象
		StuService stuService=new StuService();
		PageModel pm=stuService.findStudentsWithPage(currentNum);
		//将PageModel对象放入request
		request.setAttribute("page", pm);
		//转发到/admin/stu/stuMana.jsp
    	return "/admin/stu/stuMana.jsp";
    }
    
    //findStudentsWithPageByTeacher
    public String findStudentsWithPageByTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Teacher tea = (Teacher) request.getSession().getAttribute("teacher");
    	int teaId = tea.getTeaId();
    	//获取当前页
		int currentNum=Integer.parseInt(request.getParameter("num"));
		//调用业务层功能，返回PageModel对象
		StuService stuService=new StuService();
		PageModel pm=stuService.findStudentsWithPageByTeacher(currentNum,teaId);
		//将PageModel对象放入request
		request.setAttribute("page", pm);
		//转发到/atea/stu/stuMana.jsp
    	return "/atea/stu/stuMana.jsp";
    }
    
    //addStudentUI
    public String addStudentUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	return "/admin/stu/stuAdd.jsp";
    }
    
    //addStudentUIByTeacher
    public String addStudentUIByTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	return "/atea/stu/stuAdd.jsp";
    }
    
    //addStudent
    public String addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//接受参数
		String stuNum=request.getParameter("stuNum");
		String stuRealname=request.getParameter("stuRealname");
		String stuSex=request.getParameter("stuSex");
		String stuAge=request.getParameter("stuAge");
		String loginPw=request.getParameter("loginPw");
		String cNum= request.getParameter("cNum");
		
    	Student stu = new Student();
    	stu.setStuNum(stuNum);
    	stu.setStuRealname(stuRealname);
    	stu.setStuSex(stuSex);
    	stu.setStuAge(stuAge);
    	stu.setLoginPw(loginPw);
    	stu.setcNum(cNum);
    	StuService stuService=new StuService();
    	stuService.addStudent(stu);
    	//重定向到查询学生信息功能
		response.sendRedirect("/czjf_system/StuServlet?method=findStudentsWithPage&num=1");
		return null;
    }
    
  //addStudentByTeacher
    public String addStudentByTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//接受参数
		String stuNum=request.getParameter("stuNum");
		String stuRealname=request.getParameter("stuRealname");
		String stuSex=request.getParameter("stuSex");
		String stuAge=request.getParameter("stuAge");
		String loginPw=request.getParameter("loginPw");
		String cNum= request.getParameter("cNum");
		
    	Student stu = new Student();
    	stu.setStuNum(stuNum);
    	stu.setStuRealname(stuRealname);
    	stu.setStuSex(stuSex);
    	stu.setStuAge(stuAge);
    	stu.setLoginPw(loginPw);
    	stu.setcNum(cNum);
    	StuService stuService=new StuService();
    	stuService.addStudent(stu);
    	//重定向到查询学生信息功能
		response.sendRedirect("/czjf_system//StuServlet?method=findStudentsWithPageByTeacher&num=1");
		return null;
    }
    
    //delStudentById
    public String delStudentById(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//获取待删除学生的编号
		String id=request.getParameter("id");
		//调用业务层删除学生功能
		StuService stuService=new StuService();
		stuService.delStudentById(id);
		//重定向到/StuServlet?method=findStudentsWithPage
		response.sendRedirect("/czjf_system//StuServlet?method=findStudentsWithPage&num=1");
		return null;
    }
    
    //patherAddStudentUIByTeacher
    public String patherAddStudentUIByTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/atea/stu/patcherAdd.jsp";
    }
    
  //patherAddStudentUIByAdmin
    public String patherAddStudentUIByAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/admin/stu/patcherAdd.jsp";
    }
    
    //patherAddStudent（atea和admin共用）
    public String patherAddStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        PatcherAddStudent.readExcel(path);
    	//4_重定向到查询学生信息功能
		response.sendRedirect("/czjf_system/StuServlet?method=findStudentsWithPageByTeacher&num=1");
		return null;
    }
    
}
