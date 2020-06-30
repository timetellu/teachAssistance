package cn.itcast.czjf.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.czjf.web.base.BaseServlet;

public class TaskServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    //addTaskUI
	public String addTaskUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return  "/atea/task/taskAdd.jsp";
	}
	
	//findTasksWithPageByTeacher
	public String findTasksWithPageByTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return  "/atea/task/taskMana.jsp";
	}
	
	//taskDetailList
	public String taskDetailList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return  "/atea/task/taskDetailMana.jsp";
	}

}
