package cn.itcast.czjf.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.czjf.dao.StuDao;
import cn.itcast.czjf.dao.TeacherDao;
import cn.itcast.czjf.domain.Student;
import cn.itcast.czjf.domain.Teacher;
import cn.itcast.czjf.utils.PageModel;

public class StuService {

	public Student validateUserExist(String um) throws SQLException {
		// 调用DAO层功能
		StuDao stuDao = new StuDao();
		return stuDao.validateUserExist(um);

	}

	public Student stuLogin(String um, String up) throws SQLException {
		// 调用DAO层功能
		StuDao stuDao = new StuDao();
		return stuDao.stuLogin(um,up);
	}

	public PageModel findStudentsWithPage(int currentNum) throws SQLException {
		//1_创建PageModel对象,计算分页参数
		StuDao stuDao = new StuDao();
		int totalRecords=stuDao.findTotalRecordsByXK();
		PageModel pm=new PageModel(currentNum,totalRecords,5);
		//2_为PageModel对象设置集合(当前页中的学生信息)
		//select * from t_stu limit ? , ?
		List<Student> list=stuDao.findStudentsWithPage((currentNum-1)*5,5);
		pm.setList(list);
		//3_为PageModel对象设置url
		pm.setUrl("StuServlet?method=findStudentsWithPage");
		return pm;
	}

	public void delStudentById(String id) throws SQLException {
		StuDao stuDao = new StuDao();
		stuDao.delStudentById(id);
		
	}

	public void addStudent(Student stu) throws SQLException {
		StuDao stuDao = new StuDao();
		stuDao.addStudent(stu);
	}

	public PageModel findStudentsWithPageByTeacher(int currentNum, int teaId) throws SQLException {
		//1_创建PageModel对象,计算分页参数
		StuDao stuDao = new StuDao();
		int totalRecords=stuDao.findTotalRecordsByTeacher(teaId);
		PageModel pm=new PageModel(currentNum,totalRecords,5);
		//2_为PageModel对象设置集合(当前页中的学生信息)
		//select * from t_stu limit ? , ?
		List<Student> list=stuDao.findStudentsWithPageByTeacher(teaId,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_为PageModel对象设置url
		pm.setUrl("StuServlet?method=findStudentsWithPageByTeacher");
		return pm;
	}

}
