package cn.itcast.czjf.web.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.itcast.czjf.domain.Course;
import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.domain.Student;
import cn.itcast.czjf.domain.Teacher;
import cn.itcast.czjf.service.CourseService;
import cn.itcast.czjf.service.DocumentService;
import cn.itcast.czjf.utils.DownLoadUtils;
import cn.itcast.czjf.utils.PageModel;
import cn.itcast.czjf.utils.UploadUtils;
import cn.itcast.czjf.web.base.BaseServlet;

public class DocumentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

   
	public String findPrevDocument(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//1_调用业务层功能，返回存储着Document对象的集合
		DocumentService DocumentService = new DocumentService();
		List<Document> list = DocumentService.findPrevDocument();  //查询5个最新资料,返回集合
		//2_将集合放入request域对象内
		request.setAttribute("list", list);
		//3_转发到/site/doc/docPrev.jsp
		return "/site/doc/docPrev.jsp";

	}

	
	//findDocumentById
	public String findDocumentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int docId =Integer.parseInt(request.getParameter("id"));
		//1_调用业务层功能，返回存储着Document对象
		DocumentService DocumentService = new DocumentService();
		Document doc = DocumentService.findDocumentById(docId);  //通过id查询资料详情
		//2_将集合放入request域对象内
		request.setAttribute("doc", doc);
		//3_转发到/site/doc/docDetail.jsp
		return "/site/doc/docDetail.jsp";
	}
	
	
	//findDocumentsWithPage（学生端查询所有课程的所有教学资料）
	public String findDocumentsWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//1_接受当前页参数
		Student stu = (Student) request.getSession().getAttribute("stu");
		int sId = stu.getStuId();
		int num =Integer.parseInt(request.getParameter("num"));
		//2_调用业务层功能，返回PageModel
		DocumentService DocumentService = new DocumentService();
		PageModel pm = DocumentService.findDocumentsWithPage(num,sId);
		//3_将PageModel放入request
		request.setAttribute("page", pm);
		//4_转发到/site/doc/docAll.jsp
		return "/site/doc/docAll.jsp";
		
	}
	
	//findDocumentsWithPageByCourse（学生端查询某课程对应的教学资料）
	public String findDocumentsWithPageByCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//1_接受当前页参数
		int num =Integer.parseInt(request.getParameter("num"));
		int cId =Integer.parseInt(request.getParameter("cId"));
		//2_调用业务层功能，返回PageModel
		DocumentService DocumentService = new DocumentService();
		PageModel pm = DocumentService.findDocumentsWithPageByCourse(num,cId);
		//3_将PageModel放入request
		request.setAttribute("page", pm);
		//------查询课程名的过程
		//1_调用业务层功能，返回存储着Course对象
    	CourseService CourseService=new CourseService();
		Course cour = CourseService.findCourseById(cId);  //通过id查询资料详情
		//2_将集合放入request域对象内
		request.setAttribute("cour", cour);
		//4_转发到/site/doc/docAll.jsp
		return "/site/doc/docAll.jsp";
		
	}
	
	//downloadDocument
	public String downloadDocument(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//接受客户端资料的id
		int docId = Integer.parseInt(request.getParameter("id"));
		//调用service功能，根据资料的id获取资料对象
		DocumentService DocumentService = new DocumentService();
		Document doc = DocumentService.findDocumentById(docId);  //通过id查询资料详情
		//获取到项目下upload目录的绝对路径
		String realPath = getServletContext().getRealPath("/upload");
		//实例化一个File代表，代表待下载的资料
		File file=new File(realPath,doc.getDocAttachment());

		DownLoadUtils.setConentType(request, doc.getDocAttachment(), response);
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
	
	//findDocumentsWithPageByTeacher
	public String findDocumentsWithPageByTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//1_接受当前页参数
		int num =Integer.parseInt(request.getParameter("num"));
		int tId = ((Teacher) request.getSession().getAttribute("teacher")).getTeaId();
		//2_调用业务层功能，返回PageModel
		DocumentService DocumentService = new DocumentService();
		PageModel pm = DocumentService.findDocumentsWithPageByTeacher(num,tId);
		//3_将PageModel放入request
		request.setAttribute("page", pm);
		//4_转发到 /atea/doc/docMana.jsp
		return "/atea/doc/docMana.jsp";
	}
	
	//deleteDocumentByTeacher
	public String deleteDocumentByTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//1_获取待删除资料的编号
		int docId =Integer.parseInt(request.getParameter("id"));
		//12_调用业务层功能，根据视频编号删除资料
		DocumentService DocumentService = new DocumentService();
		DocumentService.deleteDocumentByTeacher(docId);  //通过id查询到对应的资料信息
		//3_重定向到查询全部资料路径
		response.sendRedirect("/czjf_system/DocumentServlet?method=findDocumentsWithPageByTeacher&num=1");
		return null;
	}
	
	//addDocumentUI
	public String addDocumentUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		return "/atea/doc/docAdd.jsp";
	}
	
	//addDocument
	public String addDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> map=new HashMap<String,String>();//携带表单名称以及表单参数
		Document document=new Document(); //携带Document数据，向 service,dao进行传递
		
//		String docName=request.getParameter("docName"); 
//		System.out.println(docName);  //null，因为设置了enctype="multipart/form-data"
		
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
				//如果是普通项：获取到对应的表单名称和表单内容     Eg: docName<__>333333333 
				String name=item.getFieldName();
				String value=new String(item.getString().getBytes("ISO8859-1"),"utf-8");  //必须进行转码，不然会出现中文乱码
				System.out.println(value);
				map.put(name, value);
			}else {
				//如果是上传项：在服务端指定目录/upload/ 创建一个文件，将上传项中文件的二进制数据输出到创建好的文件中
				//获取到文件名称
				String fName=item.getName();
//				System.out.println(fName);
				//获取服务端upload真实路径
				String realPath=getServletContext().getRealPath("/upload/");
				System.out.println(realPath);
				String uuidName=UploadUtils.getUUIDName(fName);
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
		response.sendRedirect("/czjf_system/DocumentServlet?method=findDocumentsWithPageByTeacher&num=1");
		return null;
	}

}
