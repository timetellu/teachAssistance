package cn.itcast.czjf.web.servlets;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.itcast.czjf.domain.Course;
import cn.itcast.czjf.domain.Student;
import cn.itcast.czjf.domain.Teacher;
import cn.itcast.czjf.domain.Vedio;
import cn.itcast.czjf.service.CourseService;
import cn.itcast.czjf.service.DocumentService;
import cn.itcast.czjf.service.VedioService;
import cn.itcast.czjf.utils.DownLoadUtils;
import cn.itcast.czjf.utils.PageModel;
import cn.itcast.czjf.utils.UploadUtils;
import cn.itcast.czjf.web.base.BaseServlet;

public class VedioServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findPrevVedio(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1_调用业务层功能，返回存储着Vedio对象的集合
		VedioService VedioService=new VedioService();
		List<Vedio> list=VedioService.findPrevVedio();//查询5个最新视频,返回集合
		//2_将集合放入request域对象内
		request.setAttribute("list", list);
		//3_转发到/site/vedio/vedioPrev.jsp
		return "/site/vedio/vedioPrev.jsp";
	}
	
	//findVedioWithPage（学生端查看所有课程的教学视频）
	public String findVedioWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1_接受当前页参数
		Student stu = (Student) request.getSession().getAttribute("stu");
		int sId = stu.getStuId();
		int num=Integer.parseInt(request.getParameter("num"));
		//2_调用业务层功能，返回PageModel
		VedioService VedioService=new VedioService();
		PageModel pm=VedioService.findVedioWithPage(num,sId);
		//  为什么是PageModel?:
		//   由于当前的功能时分页查询，除了查询当前页中的视频信息之外，还需要货到分页参数信息
		//   如果仅仅是当前页中的视频，用一个集合就可以实现。但是分页参数比较繁杂，所以用PageModel工具类携带这类数据 
		//3_将PageModel放入request
		request.setAttribute("page", pm);
		//4_转发到vedioAll.jsp
		return "/site/vedio/vedioAll.jsp";
	}
	
	//findVedioWithPageByCourse（学生端根据课程查看对应的教学视频）
	public String findVedioWithPageByCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1_接受当前页参数
		int num=Integer.parseInt(request.getParameter("num"));
		int cId=Integer.parseInt(request.getParameter("cId"));
		//2_调用业务层功能，返回PageModel
		VedioService VedioService=new VedioService();
		PageModel pm=VedioService.findVedioWithPageByCourse(num,cId);
		//  为什么是PageModel?:
		//   由于当前的功能时分页查询，除了查询当前页中的视频信息之外，还需要货到分页参数信息
		//   如果仅仅是当前页中的视频，用一个集合就可以实现。但是分页参数比较繁杂，所以用PageModel工具类携带这类数据 
		//3_将PageModel放入request
		request.setAttribute("page", pm);
		//----------查询课程名的过程
		//1_调用业务层功能，返回存储着Course对象
    	CourseService CourseService=new CourseService();
		Course cour = CourseService.findCourseById(cId);  //通过id查询资料详情
		//2_将集合放入request域对象内
		request.setAttribute("cour", cour);
		//4_转发到vedioAll.jsp
		return "/site/vedio/vedioAll.jsp";
	}
	
	//findVedioByVid
	public String findVedioByVid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取视频ID参数
		String vId=request.getParameter("id");
		//调用业务层功能：根据视频ID查询对应的视频对象
		VedioService VedioService=new VedioService();
		Vedio vedio=VedioService.findVedioByVid(vId);
		//将查询到的视频对象放入request
		request.setAttribute("vedio", vedio);
		//转发到/site/vedio/vedioDtail.jsp
		return "/site/vedio/vedioDetail.jsp";
	}
	
	//playVedioById
	public String playVedioById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取到视频id
		String vId=request.getParameter("id");
		//将视频id传递给service,调用service功能，返回视频对象
		VedioService VedioService=new VedioService();
		Vedio vedio=VedioService.findVedioByVid(vId);
		//将返回的视频对象放入request
		request.setAttribute("vedio", vedio);
		//转发到/play/playVedio.jsp
		return "/play/playVedio.jsp";
	}
	
	//downloadVedio
	public String downloadVedio(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受客户端视频的id
		String id=request.getParameter("id");
		//调用service功能，根据视频的id获取视频对象
		VedioService VedioService=new VedioService();
		Vedio vedio=VedioService.findVedioByVid(id);
		//获取到项目下upload目录的绝对路径
		String realPath = getServletContext().getRealPath("/upload/");
		//实例化一个File代表，代表待下载的视频。
		File file=new File(realPath,vedio.getVedioAttachment());

		DownLoadUtils.setConentType(request, vedio.getVedioAttachment(), response);
		//通过response对象设置一对消息头
		//response.setHeader("Content-disposition", "attachment;filename="+vedio.getVedioAttachment());
		
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

	//findVediosWithPageByTeacher
	public String findVediosWithPageByTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//从session中获取当前登录的老师信息
		int tId = ((Teacher) request.getSession().getAttribute("teacher")).getTeaId();
		//获取当前页
		int currentPageNum=Integer.parseInt(request.getParameter("num"));
		//调用业务层功能:带分页形式查询视频信息，返回PageModel(1_当前页中的视频信息2_计算好的分页参数3_url)
		VedioService VedioService=new VedioService();
		PageModel pm=VedioService.findVediosWithPageByTeacher(currentPageNum,tId);
		//将PageModel放入request
		request.setAttribute("page", pm);
		//转发到vedioMana.jsp
		return "/atea/vedio/vedioMana.jsp";
	}
	//deleteVedioByTeacher
	public String deleteVedioByTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取待删除视频的编号
		String vId=request.getParameter("id");
		//调用业务层功能，根据视频编号删除视频
		VedioService VedioService=new VedioService();
		VedioService.deleteVedioByTeacher(vId);
		//重定向到查询全部商品路径
		response.sendRedirect("/czjf_system/VedioServlet?method=findVediosWithPageByTeacher&num=1");
		return null;
	}
	//addVedioUI
	public String addVedioUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/atea/vedio/vedioAdd.jsp";
	}
	
	//addVedio
	public String addVedio(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> map=new HashMap<String,String>();//携带表单名称以及表单参数
		Vedio vedio=new Vedio(); //携带Vedio数据，向 service,dao进行传递
		
		//1_创建DiskFiletemFactory对象设置允许上传文件大小
		DiskFileItemFactory fac=new DiskFileItemFactory();
		fac.setSizeThreshold(1024*1024*200); //允许上传文件的最大为200MB
		//2_创建ServletFileUpload upload
		ServletFileUpload upload=new ServletFileUpload(fac);
		//3_通过upload解析request,得到集合<FileItem>
		// FileItem代表什么？工具就将请求体中每对分割线中间的内容封装为一个FileItem对象
		List<FileItem> list=upload.parseRequest(request);
		//4_遍历集合
		for (FileItem item : list) {
			//5_判断当前FileItem是普通项还是上传项？
			//什么是普通项：表单中的普通字段，非上传字段
			//什么是上传项：表单中包含file组件上传项，携带着上传到服务端文件
			//item.isFormField()  ;;判断当前的item是否是表单项目
			if(item.isFormField()) {
				//普通项
				//如果是普通项：获取到对应的表单名称和表单内容     Eg: vedioName<__>333333333 
				String name=item.getFieldName();
				String value=new String(item.getString().getBytes("ISO8859-1"),"utf-8");  //必须进行转码，不然会出现中文乱码
//				System.out.println(value);
				map.put(name, value);
			}else {
				//如果是上传项：在服务端指定目录/upload/ 创建一个文件，将上传项中文件的二进制数据输出到创建好的文件中
				//获取到文件名称
				String fName=item.getName();
				//System.out.println("文件名称"+fName); //11.mp4
				//获取服务端upload真实路径
				String realPath=getServletContext().getRealPath("/upload/");
				String uuidName=UploadUtils.getUUIDName(fName);
				//XXXXXX.mp4
				//在服务端指定路径下创建文件
				File f=new File(realPath,uuidName);
				if(!f.exists()) {
					f.createNewFile();
					//创建文件此时其中没有内容
				}
				item.write(f);//将上传到服务端的文件中的二进制数据输出到文件中
				map.put("vedioAttachment", uuidName);
				map.put("attachmentOldName", fName);
			}
		}
		//将MAP中的数据封装在Vedio对象上
		BeanUtils.populate(vedio, map);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		vedio.setUploadTime(sdf.format(new Date()));
		vedio.setDel("no");
		vedio.setStatus(1);
		//6_将普通项的数据以及文件的位置传递到service,dao.进行数据的保存
		VedioService VedioService=new VedioService();
		VedioService.addVedio(vedio);
		
		response.sendRedirect("/czjf_system/VedioServlet?method=findVediosWithPageByTeacher&num=1");
		return null;
	}
	
}




