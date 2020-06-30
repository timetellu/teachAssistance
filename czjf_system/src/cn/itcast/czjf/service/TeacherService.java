package cn.itcast.czjf.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.czjf.dao.TeacherDao;
import cn.itcast.czjf.domain.Teacher;
import cn.itcast.czjf.utils.PageModel;

public class TeacherService {

	public Teacher teacherLogin(String um, String up)  throws SQLException{
		//调用DAO层登录功能
		TeacherDao TeacherDao=new TeacherDao();
		return TeacherDao.teacherLogin(um,up);
		
	}

	public PageModel findTeachersWithPage(int currentNum) throws SQLException {
		//1_创建PageModel对象,计算分页参数
		TeacherDao TeacherDao=new TeacherDao();
		int totalRecords=TeacherDao.findTotalRecords();
		PageModel pm=new PageModel(currentNum,totalRecords,5);
		//2_为PageModel对象设置集合(当前页中的老师信息)
		//select * from t_tea limit ? , ?
		List<Teacher> list=TeacherDao.findTeachersWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_为PageModel对象设置url
		pm.setUrl("TeacherServlet?method=findTeachersWithPage");
		return pm;
	}

	public void addTeacher(Teacher teacher)  throws SQLException {
		TeacherDao TeacherDao=new TeacherDao();
		TeacherDao.addTeacher(teacher);
	}

	public void delTeacherById(String id)  throws SQLException {
		TeacherDao TeacherDao=new TeacherDao();
		TeacherDao.delTeacherById(id);
	}

	public void updateTeacher(Teacher t)throws SQLException {
		TeacherDao TeacherDao=new TeacherDao();
		TeacherDao.updateTeacher(t);
	}

	public Teacher validateTeaExist(String tn) throws SQLException {
		TeacherDao TeacherDao=new TeacherDao();
		return TeacherDao.validateTeaExist(tn);
	}

}
