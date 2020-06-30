package cn.itcast.czjf.web.servlets;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.czjf.domain.Message;
import cn.itcast.czjf.domain.Student;
import cn.itcast.czjf.service.MessageService;
import cn.itcast.czjf.utils.PageModel;
import cn.itcast.czjf.web.base.BaseServlet;

public class MessageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	public String findPrevMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1_调用业务层功能，返回存储着Message对象的集合
		MessageService messageService=new MessageService();
		List<Message> list=messageService.findPrevMessage();//查询5个最新留言, 返回集合
		//2_将集合放入request域对象内
		request.setAttribute("list", list);
		//3_转发到/site/message/messagePrev.jsp
		return "/site/message/messagePrev.jsp";
	}
	
	//查看当前学生的师生交流内容带分页功能
	public  String findMessagesWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取当前页
		int currentNum=Integer.parseInt(request.getParameter("num"));
		//获取在session中的学生信息
		Student stu = (Student)(request.getSession().getAttribute("stu"));
		//调用业务层功能，返回PageModel (1_当前页的分页参数2_当前页的留言信息3_url)
		MessageService messageService=new MessageService();
		PageModel pm=messageService.findMessagesWithPage(currentNum,stu);
		//将PageModel放入request
		request.setAttribute("page", pm);
		//转发到/site/message/messageAll.jsp
		return "/site/message/messageAll.jsp";
	}

//	//findMessageById
	public  String findMessageById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//学生端通过id查看浏览详细信息
		int messageId = Integer.parseInt(request.getParameter("id"));
		MessageService messageService = new MessageService();
		Message message = messageService.findMessageById(messageId);
		request.setAttribute("message", message);
		return "/site/message/messageDetail.jsp";
	}
	
	//addMessageUI
	public  String addMessageUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//仅仅是一个空跳转  1_mvc 2_有时候只能转发过来
		return "/site/message/messageAdd.jsp";
	}
	
	//addMessage
	public  String addMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受参数问题
		String content = request.getParameter("content");
		//获取session中的学生信息
		Student stu=(Student)(request.getSession().getAttribute("stu"));
		//创建Message对象
		Message msg=new Message();
		//为Message对象的content,leaveWordTime属性赋予值
		msg.setContent(content);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		msg.setLeaveWordTime(sdf.format(new Date()));
		msg.setStuId(stu.getStuId());
		//调用业务层添加Message功能
		MessageService MessageService=new MessageService();
		MessageService.addMessage(msg);
		//转发到分页查询全部message模块
		response.sendRedirect("/czjf_system/MessageServlet?method=findMessagesWithPage&num=1");
		return null;
	}
	//
	public  String findMessagesWithPageByTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取当前页
		int currentPageNum=Integer.parseInt(request.getParameter("num"));
		//调用业务层功能，返回PageModel对象(1_第1页留言信息 2_分页参数信息 3_url)
		MessageService MessageService=new MessageService();
		PageModel pm=MessageService.findMessagesWithPageByTeacher(currentPageNum);
		//将PageModel放入request域对象
		request.setAttribute("page", pm);
		//转发到 /atea/message/messageMana.jsp
		return "/atea/message/messageMana.jsp";
	}
	//replayUI
	public  String replayUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受正在回复问题的编号
		String msgId=request.getParameter("id");
		//将编号放入request
		request.setAttribute("msgId", msgId);
		//转发到/atea/message/messageReplay.jsp
		return "/atea/message/messageReplay.jsp";
	}
	
	//replayMessage  回复学生问题
	public  String replayMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受回复内容参数
		String replay=request.getParameter("replay");
		//接受问题的id
		String id=request.getParameter("id");
		//调用业务层功能：回复内容
		MessageService MessageService=new MessageService();
		MessageService.replayMessage(id,replay);
		//重定向到/MessageServlet?method=findMessagesWithPageByTeacher&num=1
		response.sendRedirect("/czjf_system/MessageServlet?method=findMessagesWithPageByTeacher&num=1");
		return null;
	}
}
